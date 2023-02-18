package com.breaktime.mustune.music.api

import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class MusicEntry : ComposableFeatureEntry {
    final override val featureRoute = "music"

    fun destination() = featureRoute
}