package com.breaktime.explore.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breaktime.common.composable.components.MusicItem
import com.breaktime.common.composable.components.SearchField
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExploreScreen() {
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
            onChangeSearchText = { searchText = it }
        )

        val pagerState = rememberPagerState()
        val scope = rememberCoroutineScope()

        val musicTabs = listOf(MusicTab.Explore, MusicTab.Favorite)

        ExploreMusicTabs(
            modifier = Modifier.padding(vertical = 12.dp),
            tabsNames = musicTabs,
            currentTab = pagerState.currentPage,
            onChangeTab = { scope.launch { pagerState.animateScrollToPage(it) } }
        )

        HorizontalPager(
            count = musicTabs.size,
            state = pagerState
        ) {
            if (musicTabs.size < it) Text(text = "Something went wrong")
            else {
                val list = when (musicTabs[it]) {
                    MusicTab.Explore -> remember {
                        generateSequence { Random.nextInt(1, 256) }.take(30).toList()
                    }

                    MusicTab.Favorite -> remember {
                        generateSequence { Random.nextInt(1, 256) }.take(15).toList()
                    }
                }

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(list) { index, item ->
                        MusicItem(
                            title = "Title $index",
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
        }
    }
}

@Preview
@Composable
internal fun ExploreScreenPreview() {
    ExploreScreen()
}