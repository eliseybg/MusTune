package com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class SongBottomSheetContent private constructor(
    internal val bottomSheetLines: List<SongBottomSheetRow>
) {
    class Builder {
        private val bottomSheetLines = mutableListOf<SongBottomSheetRow>()

        fun addRow(
            @DrawableRes iconId: Int,
            @StringRes textId: Int,
            onClick: () -> Unit
        ) = this.also { bottomSheetLines.add(SongBottomSheetRow(iconId, textId, onClick)) }

        fun build() = SongBottomSheetContent(bottomSheetLines)
    }

    companion object {
        fun build(block: Builder.() -> Unit) = Builder().apply { block() }.build()
    }
}

internal data class SongBottomSheetRow(
    @DrawableRes val iconId: Int,
    @StringRes val textId: Int,
    val onClick: () -> Unit
)