package com.breaktime.mustune.musicmanager.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import com.breaktime.mustune.musicmanager.impl.data.source.songs.local.SongsDatabase
import com.breaktime.mustune.musicmanager.impl.domain.mapper.toShareType
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import java.io.File
import javax.inject.Inject

class ClearStorageUseCase @Inject constructor(
    private val songsRepository: SongsRepository
) : BaseOutcomeUseCase<Unit, ClearStorageUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Unit> {
        songsRepository.clearData()
        return Outcome.Success.Empty
    }

    object Params
}