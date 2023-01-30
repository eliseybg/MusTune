package com.breaktime.mustune

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MusicItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onItemClick: () -> Unit,
    onMoreClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(
                start = 32.dp,
                end = 28.dp,
                top = 12.dp,
                bottom = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = description,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Icon(
            modifier = Modifier.clickable { onMoreClick() },
            imageVector = Icons.Default.MoreVert,
            contentDescription = "more icon"
        )
    }
}

@Preview
@Composable
fun MusicItemPreview() {
    val list = listOf(1, 2, 3, 4, 5)
    LazyColumn {
        itemsIndexed(list) { index, item ->
            MusicItem(
                title = "Title",
                description = "Description",
                onItemClick = {},
                onMoreClick = {}
            )
            if (index < list.lastIndex)
                Divider(
                    color = Color(0xFFD6D6D6),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
        }
    }
}