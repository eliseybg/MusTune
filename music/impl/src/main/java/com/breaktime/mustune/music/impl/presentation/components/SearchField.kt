package com.breaktime.mustune.music.impl.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.common.R
import com.breaktime.mustune.common.composable.EmptyTextField

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    clearSearchText: () -> Unit = {},
    onFilterClick: () -> Unit = {},
    isFilterActive: Boolean = false
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "search icon"
        )
        EmptyTextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 10.dp),
            value = searchText,
            onValueChange = onChangeSearchText,
            hint = "Search",
            singleLine = true
        )
        if (searchText.isNotEmpty())
            Icon(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(16.dp)
                    .clickable { clearSearchText() },
                imageVector = Icons.Default.Clear,
                contentDescription = "clear icon"
            )
        Icon(
            modifier = Modifier.clickable { onFilterClick() },
            painter = if (isFilterActive) painterResource(id = R.drawable.ic_sliders_filled)
            else painterResource(id = R.drawable.ic_sliders_outlined),
            contentDescription = "filter icon",
            tint = if (isFilterActive) Color.Black else Color.Black.copy(alpha = 0.8f)
        )
    }
}

@Preview
@Composable
internal fun SearchFieldPreview() {
    var searchText by remember { mutableStateOf("") }
    SearchField(
        searchText = searchText,
        onChangeSearchText = { searchText = it },
        clearSearchText = { searchText = "" }
    )
}