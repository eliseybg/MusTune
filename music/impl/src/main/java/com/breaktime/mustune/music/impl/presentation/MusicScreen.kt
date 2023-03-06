package com.breaktime.mustune.music.impl.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import com.breaktime.mustune.music.impl.presentation.components.MusicItem
import com.breaktime.mustune.common.composable.Toolbar
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MusicScreen(viewModel: MusicViewModel, navController: NavHostController) {
    val state by viewModel.uiState.collectAsState()

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Toolbar(
                title = "Music",
                actions = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                    )
                },
                bottomContent = {
                    ExploreMusicTabs(
                        tabsNames = state.screenTabs,
                        currentTab = pagerState.currentPage,
                        onChangeTab = { scope.launch { pagerState.animateScrollToPage(it) } }
                    )
                }
            )
        }
    ) {
        val tabsItems = state.tabsSetup.map { it.songs.collectAsLazyPagingItems() }
        HorizontalPager(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFFFDFDFD))
                .padding(horizontal = 16.dp),
            count = state.screenTabs.size,
            state = pagerState
        ) { currentIndex ->
            val items = tabsItems[currentIndex]
            if (currentIndex > state.screenTabs.size) Text(text = "Something went wrong")
            else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    itemsIndexed(items) { index, item ->
                        item?.let {
                            MusicItem(
                                title = item.title,
                                author = item.author,
                                onItemClick = {},
                                onMoreClick = {}
                            )
                            if (index < items.itemSnapshotList.lastIndex)
                                Divider(color = Color(0xFFD6D6D6), thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MusicScreen() {
    val musicTabs = listOf(MusicTab.EXPLORE, MusicTab.FAVOURITE, MusicTab.PERSONAL, MusicTab.SHARED)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Toolbar(
                title = "Music",
                actions = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                    )
                },
                bottomContent = {
                    ExploreMusicTabs(
                        tabsNames = musicTabs,
                        currentTab = pagerState.currentPage,
                        onChangeTab = { scope.launch { pagerState.animateScrollToPage(it) } }
                    )
                }
            )
        }
    ) {
        HorizontalPager(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFFFDFDFD))
                .padding(horizontal = 16.dp),
            count = musicTabs.size,
            state = pagerState
        ) {
            if (musicTabs.size < it) Text(text = "Something went wrong")
            else {
                val list = remember {
                    generateSequence { Random.nextInt(1, 256) }.take(30).toList()
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    itemsIndexed(list) { index, item ->
                        MusicItem(
                            title = "Title $index",
                            author = "Description",
                            onItemClick = {},
                            onMoreClick = {}
                        )
                        if (index < list.lastIndex)
                            Divider(color = Color(0xFFD6D6D6), thickness = 1.dp)
                    }
                }
            }
        }
    }
}

@Composable
internal fun ExploreMusicTabs(
    modifier: Modifier = Modifier,
    tabsNames: List<MusicTab>,
    currentTab: Int,
    onChangeTab: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = currentTab,
        backgroundColor = Color.White,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(5.dp)),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .height(20.dp)
                    .tabIndicatorOffset(tabPositions[currentTab]),
                color = Color(0xFF0F235E)
            )
        },
        edgePadding = 0.dp,
        divider = {}
    ) {
        tabsNames.forEachIndexed { index, tab ->
            val selected = currentTab == index
            Tab(
                selected = selected,
                onClick = { onChangeTab(index) },
                text = {
                    Text(
                        text = tab.name,
                        color = if (selected) Color(0xFF0F235E) else Color(0xFF7C7C7C),
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun ExploreMusicTabPreview() {
    val musicTabs = listOf(MusicTab.EXPLORE, MusicTab.FAVOURITE, MusicTab.PERSONAL, MusicTab.SHARED)
    var currentTab by remember { mutableStateOf(0) }

    ExploreMusicTabs(
        tabsNames = musicTabs,
        currentTab = currentTab,
        onChangeTab = { currentTab = it }
    )
}

@Preview
@Composable
internal fun ExploreScreenPreview() {
    MusicScreen()
}