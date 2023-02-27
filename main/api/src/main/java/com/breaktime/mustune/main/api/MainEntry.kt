package com.breaktime.mustune.main.api

import com.breaktime.mustune.common.NavigationFeatureEntry

abstract class MainEntry : NavigationFeatureEntry {
    final override val featureRoute = "main"

    fun destination() = featureRoute
}