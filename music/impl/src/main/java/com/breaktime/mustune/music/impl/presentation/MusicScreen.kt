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
import com.breaktime.mustune.common.composable.components.MusicItem
import com.breaktime.mustune.common.composable.components.SearchField
import com.breaktime.mustune.common.composable.elements.Toolbar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun MusicScreen(viewModel: MusicViewModel, navController: NavHostController) {
    val state = viewModel.uiState.collectAsState()

    MusicScreen()
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MusicScreen() {
    var searchText by remember { mutableStateOf("") }
    val musicTabs = listOf(MusicTab.Explore, MusicTab.Favorite, MusicTab.Personal, MusicTab.Shared)
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
                .padding(start = 16.dp, end = 16.dp, top = 12.dp),
            count = musicTabs.size,
            state = pagerState
        ) {
            if (musicTabs.size < it) Text(text = "Something went wrong")
            else {
                val list = remember {
                    generateSequence { Random.nextInt(1, 256) }.take(30).toList()
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
    TabRow(
        selectedTabIndex = currentTab,
        backgroundColor = Color.White,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(5.dp)),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[currentTab]),
                color = Color(0xFF0F235E)
            )
        },
    ) {
        tabsNames.forEachIndexed { index, tab ->
            val selected = currentTab == index
            Tab(
                modifier = Modifier.height(22.dp),
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
    val musicTabs = listOf(MusicTab.Explore, MusicTab.Favorite, MusicTab.Personal, MusicTab.Shared)
    var currentTab by remember { mutableStateOf(0) }

    ExploreMusicTabs(
        tabsNames = musicTabs,
        currentTab = currentTab,
        onChangeTab = { currentTab = it }
    )
}

sealed class MusicTab(val name: String) {
    object Explore : MusicTab("explore")
    object Favorite : MusicTab("favorite")
    object Personal : MusicTab("personal")
    object Shared : MusicTab("shared")
}

@Preview
@Composable
internal fun ExploreScreenPreview() {
    MusicScreen()
}