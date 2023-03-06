package com.breaktime.mustune.musicmanager.api

import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.TabSetup

interface MusicManager {
    fun getMusicTabSetup(tab: MusicTab): TabSetup
}