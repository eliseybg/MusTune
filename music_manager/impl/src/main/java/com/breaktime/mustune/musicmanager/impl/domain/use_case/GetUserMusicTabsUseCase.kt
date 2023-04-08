package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseFlowUseCase
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserMusicTabsUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseFlowUseCase<List<MusicTab>, GetUserMusicTabsUseCase.Params>() {
    override fun execute(params: Params): Flow<List<MusicTab>> {
        return songsRepository.getUserMusicTabs(params.isForce).map { tabs ->
            tabs.map { MusicTab.valueOf(it.name) }
        }
    }

    data class Params(val isForce: Boolean)
}