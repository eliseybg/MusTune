package com.breaktime.mustune.settings.api

import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class SettingsEntry : ComposableFeatureEntry {
    final override val featureRoute = "settings"

    fun destination() = featureRoute
}