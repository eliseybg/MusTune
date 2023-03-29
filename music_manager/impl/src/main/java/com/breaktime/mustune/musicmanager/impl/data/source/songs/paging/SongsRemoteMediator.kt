package com.breaktime.mustune.musicmanager.impl.data.source.songs.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.impl.data.entities.RemoteKeysEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.impl.data.source.songs.local.SongsDatabase
import com.breaktime.mustune.musicmanager.impl.data.source.songs.remote.SongsApiService
import retrofit2.HttpException
import java.io.IOException
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalPagingApi::class)
class SongsRemoteMediator(
    private val tab: MusicTab,
    private val songsApiService: SongsApiService,
    private val songsDatabase: SongsDatabase
) : RemoteMediator<Int, SongEntity>() {
    override suspend fun initialize(): InitializeAction {
        val cacheFrequency = tab.pagingRefreshFrequency.inWholeDays
        val currentTimeInDay = System.currentTimeMillis().milliseconds.inWholeDays
        val firstSongCreationTime = songsDatabase.remoteKeysDao.getCreationTime(tab.name) ?: 0
        val firstSongCreationTimeInDay = firstSongCreationTime.milliseconds.inWholeDays

        return if (currentTimeInDay - firstSongCreationTimeInDay >= cacheFrequency) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SongEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: Constants.Pager.INITIAL_PAGE
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevPage
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                nextPage
            }
        }

        try {
            val songsResponse = songsApiService.getAllSongs(
                page = page,
                pageSize = state.config.pageSize,
                tab = tab.name
            )
            if (!songsResponse.isSuccessful) return MediatorResult.Error(Exception(songsResponse.message()))
            val songs = songsResponse.body() ?: return MediatorResult.Error(Exception("No data"))
            val endOfPaginationReached = songs.isEmpty()

            val prevPage = if (page == Constants.Pager.INITIAL_PAGE) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1

            songsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    songsDatabase.songDao.clearAllSongs(tab.name)
                    songsDatabase.remoteKeysDao.clearRemoteKeys(tab.name)
                }
                val keys = songs.map { song ->
                    RemoteKeysEntity(
                        songId = song.id,
                        prevPage = prevPage,
                        nextPage = nextPage,
                        tab = tab.name
                    )
                }
                songsDatabase.remoteKeysDao.insertAll(remoteKey = keys)
                songsDatabase.songDao.insertAll(songs)
            }
            return MediatorResult.Success(endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, SongEntity>
    ): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                songsDatabase.remoteKeysDao.getRemoteKeyBySongId(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, SongEntity>
    ): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                songsDatabase.remoteKeysDao.getRemoteKeyBySongId(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, SongEntity>
    ): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                songsDatabase.remoteKeysDao.getRemoteKeyBySongId(id = unsplashImage.id)
            }
    }
}