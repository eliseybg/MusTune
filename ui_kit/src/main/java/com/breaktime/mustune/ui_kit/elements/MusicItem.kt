package com.breaktime.mustune.ui_kit.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.resources.R

@Composable
fun MusicItem(
    modifier: Modifier = Modifier,
    title: String,
    artist: String,
    onItemClick: () -> Unit,
    onMoreClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(vertical = 12.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MusTuneTheme.colors.content
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = artist.ifEmpty { stringResource(id = R.string.no_artist) },
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontStyle = if (artist.isEmpty()) FontStyle.Italic else null,
                color = MusTuneTheme.colors.content.copy(alpha = if (artist.isNotEmpty()) 0.8f else 0.5f)
            )
        }
        Icon(
            modifier = Modifier.clickable { onMoreClick() },
            imageVector = Icons.Default.MoreVert,
            contentDescription = "more icon",
            tint = MusTuneTheme.colors.content
        )
    }
}

@Preview
@Composable
internal fun MusicItemPreview() {
    MusicItem(
        title = "Title",
        artist = "Artist",
        onItemClick = {},
        onMoreClick = {}
    )
}