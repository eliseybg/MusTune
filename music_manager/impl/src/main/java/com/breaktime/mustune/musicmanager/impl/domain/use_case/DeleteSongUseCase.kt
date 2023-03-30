package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import javax.inject.Inject

class DeleteSongUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<Unit, DeleteSongUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Unit> {
        songsRepository.deleteSong(params.songId)
        return Outcome.Success.Empty
    }

    data class Params(val songId: String)
}