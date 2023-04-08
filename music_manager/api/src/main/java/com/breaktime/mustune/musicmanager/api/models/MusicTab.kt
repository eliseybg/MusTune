package com.breaktime.mustune.musicmanager.api.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

enum class MusicTab(val icon: ImageVector, val pagingRefreshFrequency: Duration) {
    EXPLORE(Icons.Default.Explore, 1.days),
    FAVOURITE(Icons.Default.Star, 0.days),
    PERSONAL(Icons.Default.Person, 0.days),
    SHARED(Icons.Default.Share, 0.days)
}