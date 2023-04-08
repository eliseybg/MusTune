package com.breaktime.mustune.resources.theme.colors

import androidx.compose.ui.graphics.Color

class DefaultColors constructor(
    override val primary: Color = Color(0xFF0F235E),
    override val onPrimary: Color = Color(0xFFFFFFFF),
    override val secondary: Color = Color(0xFFEAEAEA),
    override val content: Color = Color(0xFF000000),
    override val deactivatedContent: Color = Color(0xFF8F8F8F),
    override val bottomSheetLine: Color = Color(0xFF898989),
    override val delete: Color = Color(0xFFFF0000),
    override val background: Color = Color(0xFFFDFDFD),
    override val divider: Color = Color(0xFFD6D6D6),
    override val toolbar: Color = Color(0xFFFFFFFF),
    override val bottomBar: Color = Color(0xFFFFFFFF),
    override val unselectedBottomBarItem: Color = Color(0xFF666666),
    override val shadowBackground: Color = Color(0x66636363),
    override val sheetColor: Color = Color(0xFFFFFFFF),
    override val switchThumb: Color = Color(0xFFFFFFFF),
    override val textHint: Color = Color(0xFF7C7C7C)
) : MusTuneColors
