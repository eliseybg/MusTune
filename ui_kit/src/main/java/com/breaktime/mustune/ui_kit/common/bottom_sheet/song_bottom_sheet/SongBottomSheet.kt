package com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.ui_kit.common.bottom_sheet.BottomSheetContent

@Composable
fun SongBottomSheet(songBottomSheetContent: SongBottomSheetContent) {
    BottomSheetContent {
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

@Composable
private fun SongBottomSheetItem(
    @DrawableRes iconId: Int,
    @StringRes textId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Icon(painter = painterResource(id = iconId), contentDescription = "edit")
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = textId),
            fontSize = 16.sp
        )
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