package com.breaktime.mustune.common.composable.bottom_sheet.song_bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.common.R
import com.breaktime.mustune.common.composable.bottom_sheet.BottomSheet

@Composable
fun SongBottomSheet(songBottomSheetContent: SongBottomSheetContent) {
    BottomSheet {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            songBottomSheetContent.bottomSheetLines.forEach { row ->
                SongBottomSheetItem(iconId = row.iconId, textId = row.textId, onClick = row.onClick)
            }
        }
    }
}

@Preview
@Composable
fun ExploreFileBottomSheetPreview() {
    SongBottomSheet(
        songBottomSheetContent = SongBottomSheetContent.Builder()
            .addRow(iconId = R.drawable.ic_favourite, textId = R.string.add_to_favourite) {}
            .addRow(iconId = R.drawable.ic_download, textId = R.string.download_file) {}
            .addRow(iconId = R.drawable.ic_link, textId = R.string.copy_link) {}
            .build()
    )
}

@Preview
@Composable
fun FavouriteFileBottomSheetPreview() {
    SongBottomSheet(
        songBottomSheetContent = SongBottomSheetContent.Builder()
            .addRow(iconId = R.drawable.ic_favourite, textId = R.string.remove_from_favourite) {}
            .addRow(iconId = R.drawable.ic_download, textId = R.string.download_file) {}
            .addRow(iconId = R.drawable.ic_link, textId = R.string.copy_link) {}
            .build()
    )
}

@Preview
@Composable
fun PersonalFileBottomSheetPreview() {
    SongBottomSheet(
        songBottomSheetContent = SongBottomSheetContent.Builder()
            .addRow(iconId = R.drawable.ic_edit, textId = R.string.edit_info) {}
            .addRow(iconId = R.drawable.ic_download, textId = R.string.download_file) {}
            .addRow(iconId = R.drawable.ic_share, textId = R.string.share_settings) {}
            .addRow(iconId = R.drawable.ic_link, textId = R.string.copy_link) {}
            .addRow(iconId = R.drawable.ic_delete, textId = R.string.delete_file) {}
            .build()
    )
}

@Preview
@Composable
fun SharedFileBottomSheetPreview() {
    SongBottomSheet(
        songBottomSheetContent = SongBottomSheetContent.Builder()
            .addRow(iconId = R.drawable.ic_download, textId = R.string.download_file) {}
            .addRow(iconId = R.drawable.ic_share, textId = R.string.share_settings) {}
            .addRow(iconId = R.drawable.ic_link, textId = R.string.copy_link) {}
            .build()
    )
}