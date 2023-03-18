package com.breaktime.mustune.share_file.api

import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class ShareFileEntry : ComposableFeatureEntry {
    final override val featureRoute = "share_file"

    fun destination() = featureRoute
}