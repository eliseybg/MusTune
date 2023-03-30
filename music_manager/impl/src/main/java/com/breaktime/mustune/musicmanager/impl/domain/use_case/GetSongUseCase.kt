package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toSong
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import javax.inject.Inject

class GetSongUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<Song, GetSongUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Song> {
        val song = songsRepository.getSong(params.songId, params.isForce)
        return Outcome.Success.Value(song!!.toSong())
    }

    data class Params(val songId: String, val isForce: Boolean)
}