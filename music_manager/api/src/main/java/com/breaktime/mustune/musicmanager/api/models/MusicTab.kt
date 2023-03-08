package com.breaktime.mustune.musicmanager.api.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class MusicTab(val icon: ImageVector) {
    EXPLORE(Icons.Default.Explore),
    FAVOURITE(Icons.Default.Star),
    PERSONAL(Icons.Default.Person),
    SHARED(Icons.Default.Share)
}