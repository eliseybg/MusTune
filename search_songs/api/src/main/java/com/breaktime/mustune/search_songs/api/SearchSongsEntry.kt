package com.breaktime.mustune.search_songs.api

import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class SearchSongsEntry : ComposableFeatureEntry {
    final override val featureRoute = "search_songs"

    fun destination() = featureRoute
}