package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.gp_core.GpPdfConverter
import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.file_manager.api.FileManager
import com.breaktime.mustune.musicmanager.api.models.SongFileInfo
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import java.io.File
import javax.inject.Inject

class GetSongFileInfoUseCase @Inject constructor(
    private val songsRepository: SongsRepository,
    private val fileManager: FileManager
) : BaseOutcomeUseCase<SongFileInfo, GetSongFileInfoUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<SongFileInfo> {
        val songFile = songsRepository.getSongFile(params.songId)
        val song = songsRepository.getSong(params.songId)
        val pdfsDir = fileManager.getPdfDir() ?: throw Exception("No dir")
        song ?: return Outcome.Failure(Exception("No file"))
        val pdfFile = File("$pdfsDir/${songFile.nameWithoutExtension}.pdf")
        GpPdfConverter.convertFileToPdf(songFile, pdfFile)

        return Outcome.Success.Value(SongFileInfo(song.title, song.artist, pdfFile))
    }

    data class Params(val songId: String)
}