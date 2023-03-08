package com.breaktime.mustune.musicmanager.impl

import androidx.paging.PagingData
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import com.breaktime.mustune.musicmanager.impl.domain.use_case.GetSongsFlowUseCase
import com.breaktime.mustune.musicmanager.impl.domain.use_case.SearchSongsFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicManagerImpl @Inject constructor(
    private val getSongsFlowUseCase: GetSongsFlowUseCase,
    private val searchSongsFlowUseCase: SearchSongsFlowUseCase
) : MusicManager {
    override fun getMusicTabSetup(tab: MusicTab): TabSetup {
        return TabSetup(
            tab = tab,
            songs = getSongsFlowUseCase.invoke(GetSongsFlowUseCase.Params(tab))
        )
    }

    override fun searchSongs(searchText: String): Flow<PagingData<Song>> {
        return searchSongsFlowUseCase.invoke(SearchSongsFlowUseCase.Params(searchText))
    }
}