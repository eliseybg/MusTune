package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import javax.inject.Inject

class EditSongUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<Unit, EditSongUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Unit> {
        songsRepository.editSong(
            params.songId,
            params.title,
            params.artist,
            params.isDownloadable,
            params.shareSettings.toShareType()
        )
        return Outcome.Success.Empty
    }

    data class Params(
        val songId: String,
        val title: String,
        val artist: String,
        val isDownloadable: Boolean,
        val shareSettings: ShareSettings
    )
}