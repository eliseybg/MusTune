package com.breaktime.mustune.musicmanager.impl

import androidx.paging.PagingData
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import com.breaktime.mustune.musicmanager.impl.domain.use_case.AddSongToFavouriteUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.AddSongUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.DeleteSongUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.EditSongUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.GetSongUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.GetSongsFlowUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.GetUserMusicTabsUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.RemoveSongFromFavouriteUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.SearchSongsFlowUseCase
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class MusicManagerImpl @Inject constructor(
    private val getSongUseCase: GetSongUseCase,
    private val getSongsFlowUseCase: GetSongsFlowUseCase,
    private val searchSongsFlowUseCase: SearchSongsFlowUseCase,
    private val getUserMusicTabsUseCase: GetUserMusicTabsUseCase,
    private val addSongUseCase: AddSongUseCase,
    private val editSongUseCase: EditSongUseCase,
    private val deleteSongUseCase: DeleteSongUseCase,
    private val addSongToFavouriteUseCase: AddSongToFavouriteUseCase,
    private val removeSongFromFavouriteUseCase: RemoveSongFromFavouriteUseCase
) : MusicManager {
    override suspend fun getSong(songId: String, isForce: Boolean): Outcome<Song> {
        return getSongUseCase.invoke(GetSongUseCase.Params(songId, isForce))
    }

    override fun getMusicTabSetup(tab: MusicTab): TabSetup {
        return TabSetup(
            tab = tab,
            songs = getSongsFlowUseCase.invoke(GetSongsFlowUseCase.Params(tab))
        )
    }

    override fun getUserMusicTabs(isForce: Boolean): Flow<List<MusicTab>> {
        return getUserMusicTabsUseCase.invoke(GetUserMusicTabsUseCase.Params(isForce))
    }

    override fun searchSongs(
        searchText: String,
        searchFilter: SearchFilter
    ): Flow<PagingData<Song>> {
        return searchSongsFlowUseCase.invoke(
            SearchSongsFlowUseCase.Params(
                searchText,
                searchFilter
            )
        )
    }

    override suspend fun addSong(
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareSettings: ShareSettings,
        file: File
    ): Outcome<Unit> {
        val params = AddSongUseCase.Params(title, artist, isDownloadable, shareSettings, file)
        return addSongUseCase.invoke(params)
    }

    override suspend fun editSong(
        songId: String,
        title: String,
        artist: String,
        isDownloadable: Boolean,
        shareSettings: ShareSettings
    ): Outcome<Unit> {
        val params = EditSongUseCase.Params(songId, title, artist, isDownloadable, shareSettings)
        return editSongUseCase.invoke(params)
    }

    override suspend fun deleteSong(songId: String): Outcome<Unit> {
        val params = DeleteSongUseCase.Params(songId)
        return deleteSongUseCase.invoke(params)
    }

    override suspend fun addToFavourite(songId: String): Outcome<Unit> {
        val params = AddSongToFavouriteUseCase.Params(songId)
        return addSongToFavouriteUseCase.invoke(params)
    }

    override suspend fun removeFromFavourite(songId: String): Outcome<Unit> {
        val params = RemoveSongFromFavouriteUseCase.Params(songId)
        return removeSongFromFavouriteUseCase.invoke(params)
    }
}