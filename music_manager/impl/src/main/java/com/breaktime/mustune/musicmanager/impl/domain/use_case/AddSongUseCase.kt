package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import java.io.File
import javax.inject.Inject

class AddSongUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<Unit, AddSongUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Unit> {
        songsRepository.addSong(
            params.title,
            params.artist,
            params.isDownloadable,
            params.shareSettings.toShareType(),
            params.file
        )
        return Outcome.Success.Empty
    }

    data class Params(
        val title: String,
        val artist: String,
        val isDownloadable: Boolean,
        val shareSettings: ShareSettings,
        val file: File
    )
}