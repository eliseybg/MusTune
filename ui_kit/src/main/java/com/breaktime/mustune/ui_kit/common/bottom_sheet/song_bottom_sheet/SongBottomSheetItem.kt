package com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SongBottomSheetItem(@DrawableRes iconId: Int, @StringRes textId: Int, onClick: () -> Unit) {
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