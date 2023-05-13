package com.breaktime.mustune.musicmanager.impl.domain.use_case

import android.net.Uri
import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.file_manager.api.FileManager
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import javax.inject.Inject

class AddSongUseCase @Inject constructor(
    private val songsRepository: SongsRepository,
    private val fileManager: FileManager
) : BaseOutcomeUseCase<Unit, AddSongUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Unit> {
        val tempFile = fileManager.getTempFile(params.file)
        tempFile ?: throw Exception("Error create temp file")
        songsRepository.addSong(
            params.title,
            params.artist,
            params.isDownloadable,
            params.shareSettings.toShareType(),
            tempFile
        )
        tempFile.delete()
        return Outcome.Success.Empty
    }

    data class Params(
        val title: String,
        val artist: String,
        val isDownloadable: Boolean,
        val shareSettings: ShareSettings,
        val file: Uri
    )
}