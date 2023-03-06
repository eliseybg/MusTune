package com.breaktime.mustune.musicmanager.impl

import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.TabSetup
import com.breaktime.mustune.musicmanager.impl.domain.use_case.GetSongsFlowUseCase
import javax.inject.Inject

class MusicManagerImpl @Inject constructor(
    private val getSongsFlowUseCase: GetSongsFlowUseCase
) : MusicManager {
    override fun getMusicTabSetup(tab: MusicTab): TabSetup {
        return TabSetup(
            tab = tab,
            songs = getSongsFlowUseCase.invoke(GetSongsFlowUseCase.Params(tab))
        )
    }
}