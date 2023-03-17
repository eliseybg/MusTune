package com.breaktime.mustune.musicmanager.api.models

import com.breaktime.mustune.common.R
import com.breaktime.mustune.common.composable.bottom_sheet.song_bottom_sheet.SongBottomSheetContent

data class Song(val id: String, val title: String, val artist: String) {
    val bottomSheetContent: SongBottomSheetContent
        get() {
            return SongBottomSheetContent.build {
                addRow(iconId = R.drawable.ic_favourite, textId = R.string.add_to_favourite) {}
                addRow(iconId = R.drawable.ic_download, textId = R.string.download_file) {}
                addRow(iconId = R.drawable.ic_link, textId = R.string.copy_link) {}
            }
        }
}