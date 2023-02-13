package com.breaktime.mustune.impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.common.composable.components.MusicItem
import com.breaktime.common.composable.components.SearchField
import kotlin.random.Random

@Composable
fun PersonalScreen() {
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFDFD))
            .padding(start = 16.dp, end = 16.dp, top = 12.dp),
    ) {
        SearchField(
            modifier = Modifier,
            searchText = searchText,
            onChangeSearchText = { searchText = it },
            clearSearchText = { searchText = "" }
        )

        val list = remember { generateSequence { Random.nextInt(1, 256) }.take(30).toList() }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(list) { index, item ->
                MusicItem(
                    title = "Title $index",
                    description = "Description",
                    onItemClick = {},
                    onMoreClick = {}
                )
                if (index < list.lastIndex)
                    Divider(color = Color(0xFFD6D6D6), thickness = 1.dp)
            }
        }
    }
}

@Preview
@Composable
internal fun ExploreScreenPreview() {
    PersonalScreen()
}