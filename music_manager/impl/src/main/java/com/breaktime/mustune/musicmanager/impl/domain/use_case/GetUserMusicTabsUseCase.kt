package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import javax.inject.Inject

class GetUserMusicTabsUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<List<MusicTab>, GetUserMusicTabsUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<List<MusicTab>> {
        val musicTabs = songsRepository.getUserMusicTabs(params.isForce)
            .map { MusicTab.valueOf(it.name) }
        return Outcome.Success.Value(musicTabs)
    }

    data class Params(val isForce: Boolean)
}