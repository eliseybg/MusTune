package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import java.io.File
import javax.inject.Inject

class AddSongToFavouriteUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<Unit, AddSongToFavouriteUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Unit> {
        songsRepository.addSongToFavourite(params.songId)
        return Outcome.Success.Empty
    }

    data class Params(val songId: String)
}