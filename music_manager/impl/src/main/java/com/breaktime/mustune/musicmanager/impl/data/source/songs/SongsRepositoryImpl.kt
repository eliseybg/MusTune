package com.breaktime.mustune.musicmanager.impl.data.source.songs

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.extentions.copyBufferedTo
import com.breaktime.mustune.file_manager.api.FileManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.impl.data.entities.ShareType
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.SongIdBody
import com.breaktime.mustune.musicmanager.impl.data.entities.SongInfoBody
import com.breaktime.mustune.musicmanager.impl.data.entities.TabQuery
import com.breaktime.mustune.musicmanager.impl.data.source.songs.local.SongsDatabase
import com.breaktime.mustune.musicmanager.impl.data.source.songs.paging.SearchSongsSource
import com.breaktime.mustune.musicmanager.impl.data.source.songs.paging.SongsRemoteMediator
import com.breaktime.mustune.musicmanager.impl.data.source.songs.remote.SongsApiService
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import com.breaktime.mustune.network.api.extentions.handleResponse
import com.breaktime.mustune.network.api.extentions.retrieveBody
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class SongsRepositoryImpl @Inject constructor(
    private val songsDatabase: SongsDatabase,
    private val songsApiService: SongsApiService,
    private val fileManager: FileManager
) : SongsRepository {
    override suspend fun getSong(
        songId: String,
        isForce: Boolean
    ): SongEntity? = withContext(Dispatchers.IO) {
        if (isForce) {
            val response = songsApiService.getSong(songId)
            return@withContext response.retrieveBody().run {
                songsDatabase.songDao.insertSongAndRead(this)
            }
        }

        return@withContext songsDatabase.songDao.getSong(songId) ?: run {
            val response = songsApiService.getSong(songId)
            response.retrieveBody().run {
                songsDatabase.songDao.insertSongAndRead(this)
            }
        }
    }

    override suspend fun getSongFile(songId: String): File = withContext(Dispatchers.IO) {
        val songsDir = fileManager.getSongsDir() ?: throw Exception("No dir")
        val request = songsApiService.downloadSong(songId)
        val response = request.execute()
        val body = response.retrieveBody()
        val header = response.headers()["Content-Disposition"]
        header ?: throw Exception("No content disposition header")
        val filename = header.replace("attachment; filename=", "").replace("\"", "")
        val path = "$songsDir/$filename"
        val output = FileOutputStream(path)
        body.byteStream().copyBufferedTo(output).close()
        return@withContext File(path)
    }

    override fun getUserMusicTabs(isForce: Boolean): Flow<List<TabQuery>> =
        songsDatabase.songDao.getSongsCategories().map {
            if (it.isEmpty() || isForce) {
                val response = songsApiService.getSongsCategories()
                response.retrieveBody()
            } else it
        }

    @OptIn(ExperimentalPagingApi::class)
    override fun getSongs(tab: MusicTab): Flow<PagingData<SongEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.Pager.PAGE_SIZE,
                prefetchDistance = Constants.Pager.PREFETCH_DISTANCE,
                initialLoadSize = Constants.Pager.INITIAL_PAGE_SIZE
            ),
            pagingSourceFactory = {
                songsDatabase.songDao.getPagingSongsInfo(TabQuery.fromString(tab.name))
            },
            remoteMediator = SongsRemoteMediator(tab, songsApiService, songsDatabase)
        ).flow
    }

    override fun searchSongs(
        searchText: String,
        searchFilter: SearchFilter
    ): Flow<PagingData<SongEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.Pager.PAGE_SIZE,
                prefetchDistance = Constants.Pager.PREFETCH_DISTANCE,
                initialLoadSize = Constants.Pager.INITIAL_PAGE_SIZE
            ),
            pagingSourceFactory = { SearchSongsSource(searchText, searchFilter, songsApiService) }
        ).flow
    }

    override suspend fun addSong(
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareType: ShareType,
        file: File
    ): Unit = withContext(Dispatchers.IO) {
        val filePart = MultipartBody.Part.createFormData("file", file.name, file.asRequestBody())
        val body = SongInfoBody(null, title, artist, isDownloadable, shareType)
        val bodyJson = Gson().toJson(body)
        val requestBody = bodyJson.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        val song = songsApiService.addSong(requestBody, filePart).retrieveBody()
        songsDatabase.songDao.insertSong(song)
    }

    override suspend fun editSong(
        songId: String,
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareType: ShareType
    ): Unit = withContext(Dispatchers.IO) {
        val body = SongInfoBody(songId, title, artist, isDownloadable, shareType)
        val song = songsApiService.editSong(body).retrieveBody()
        songsDatabase.songDao.updateSong(song)
    }

    override suspend fun deleteSong(songId: String): Unit = withContext(Dispatchers.IO) {
        songsApiService.deleteSong(songId).handleResponse()
        songsDatabase.songDao.deleteSong(songId)
    }

    override suspend fun addSongToFavourite(
        songId: String
    ): Unit = withContext(Dispatchers.IO) {
        val body = SongIdBody(songId)
        val songEntity = songsApiService.addSongToFavourite(body).retrieveBody()
        songsDatabase.songDao.updateSong(songEntity)
    }

    override suspend fun removeSongFromFavourite(
        songId: String
    ): Unit = withContext(Dispatchers.IO) {
        val body = SongIdBody(songId)
        val songEntity = songsApiService.removeSongFromFavourite(body).retrieveBody()
        songsDatabase.songDao.updateSong(songEntity)
    }

    override suspend fun clearData(): Unit = withContext(Dispatchers.IO) {
        songsDatabase.clearAllTables()
    }
}