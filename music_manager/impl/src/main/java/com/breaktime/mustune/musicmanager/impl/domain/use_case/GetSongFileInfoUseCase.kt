package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.api.models.SongFileInfo
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toSong
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import java.io.File
import javax.inject.Inject

class GetSongFileInfoUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<SongFileInfo, GetSongFileInfoUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<SongFileInfo> {
        val song = songsRepository.getSong(params.songId)
        song ?: return Outcome.Failure(Exception("No file"))
        songsRepository
        return Outcome.Success.Value(SongFileInfo(song.title, song.artist, File("")))
    }

    data class Params(val songId: String)
}