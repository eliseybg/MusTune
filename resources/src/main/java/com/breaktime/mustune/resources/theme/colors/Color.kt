package com.breaktime.mustune.resources.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

interface MusTuneColors {
    val primary: Color
    val onPrimary: Color
    val secondary: Color
    val content: Color
    val deactivatedContent: Color
    val bottomSheetLine: Color
    val delete: Color
    val background: Color
    val divider: Color
    val toolbar: Color
    val bottomBar: Color
    val unselectedBottomBarItem: Color
    val shadowBackground: Color
    val sheetColor: Color
    val switchThumb: Color
    val textHint: Color
}

internal val LocalColors = staticCompositionLocalOf<MusTuneColors> { DefaultColors() }