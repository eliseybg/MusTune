package com.breaktime.mustune.share_file.impl.presentation.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme

@Composable
fun SharedUserItem(
    modifier: Modifier = Modifier,
    email: String,
    username: String?,
    onDeleteClick: () -> Unit
) {
    Row(modifier = modifier.padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = email, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(
                text = username ?: "No username",
                fontSize = 15.sp,
                color = if (username != null) MusTuneTheme.colors.content else MusTuneTheme.colors.textHint
            )

        }
        Icon(
            modifier = Modifier.clickable(onClick = onDeleteClick),
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "delete icon"
        )
    }
}