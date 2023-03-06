package com.breaktime.mustune.musicmanager.impl.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.breaktime.mustune.common.domain.BaseFlowUseCase
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toSong
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSongsFlowUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseFlowUseCase<PagingData<Song>, GetSongsFlowUseCase.Params>() {
    override fun execute(params: Params) = songsRepository.getSongs(params.tab)
        .map { data -> data.map { songEntity -> songEntity.toSong() } }

    data class Params(val tab: MusicTab)
}