package com.breaktime.mustune.music.impl.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.compose.itemsIndexed
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.extentions.isLoading
import com.breaktime.mustune.common.extentions.rememberLazyListState
import com.breaktime.mustune.common.find
import com.breaktime.mustune.create_edit_file.api.CreateEditFileEntry
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.song.api.SongEntry
import com.breaktime.mustune.song_bottom_sheet.SongBottomSheet
import com.breaktime.mustune.ui_kit.common.Toolbar
import com.breaktime.mustune.ui_kit.common.rememberContentBottomSheetState
import com.breaktime.mustune.ui_kit.elements.MusicItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun MusicScreen(
    viewModel: MusicViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
    val state by viewModel.uiState.collectAsState()

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val songBottomSheetState = rememberContentBottomSheetState<Song>()

    LaunchedEffect(key1 = true) {
        viewModelObserver(viewModel, context, scope)
    }
    SongBottomSheet(songBottomSheetState, navController, destinations) {
        Scaffold(
            topBar = {
                Toolbar(
                    content = {
                        Text(
                            text = stringResource(R.string.music),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MusTuneTheme.colors.content
                        )
                    },
                    actions = {
                        Row {
                            Icon(
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .size(24.dp)
                                    .clickable {
                                        val route = destinations
                                            .find<CreateEditFileEntry>()
                                            .destination()
                                        navController.navigate(route)
                                    },
                                painter = painterResource(id = R.drawable.ic_add_music),
                                contentDescription = "Add music",
                                tint = MusTuneTheme.colors.content
                            )
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        val route =
                                            destinations.find<SearchSongsEntry>().featureRoute
                                        navController.navigate(route)
                                    },
                                imageVector = Icons.Default.Search,
                                contentDescription = "search icon",
                                tint = MusTuneTheme.colors.content
                            )
                        }
                    },
                    bottomContent = {
                        AnimatedVisibility(visible = state.screenTabs.size > 1) {
                            ExploreMusicTabs(
                                tabsNames = state.screenTabs,
                                currentTab = pagerState.currentPage,
                                onChangeTab = { scope.launch { pagerState.animateScrollToPage(it) } }
                            )
                        }
                    }
                )
            }
        ) {
            val tabsItems = state.tabsSetup.map { it.songs.collectAsLazyPagingItems() }
            if (tabsItems.isNotEmpty()) HorizontalPager(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(MusTuneTheme.colors.background)
                    .padding(horizontal = 16.dp),
                count = state.screenTabs.size,
                state = pagerState
            ) { currentIndex ->
                val items = tabsItems[currentIndex]
                if (currentIndex > state.screenTabs.size) Text(text = stringResource(R.string.something_went_wrong))
                else {
                    val pullRefreshState = rememberPullRefreshState(
                        refreshing = items.loadState.refresh == LoadState.Loading,
                        onRefresh = {
                            viewModel.setEvent(MusicContract.Event.UpdateSongTabs(true))
                            items.refresh()
                        }
                    )
                    Box(Modifier.pullRefresh(pullRefreshState)) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 12.dp),
                            state = items.rememberLazyListState()
                        ) {
                            items(
                                count = items.itemCount,
                                key = items.itemKey(),
                                contentType = items.itemContentType(
                                )
                            ) { index ->
                                val song = items[index]
                                song?.let {
                                    MusicItem(
                                        title = song.title,
                                        artist = song.artist,
                                        onItemClick = {
                                            val route =
                                                destinations.find<SongEntry>().destination(song.id)
                                            navController.navigate(route)
                                        },
                                        onMoreClick = { songBottomSheetState.show(song) }
                                    )
                                    if (index < items.itemSnapshotList.lastIndex)
                                        Divider(
                                            color = MusTuneTheme.colors.divider,
                                            thickness = 1.dp
                                        )
                                }
                            }
                            when {
                                items.loadState.isLoading -> {
                                    item {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            CircularProgressIndicator(color = MusTuneTheme.colors.primary)
                                        }
                                    }
                                }

                                items.itemCount == 0 && !items.loadState.isLoading -> {
                                    item {
                                        Box(
                                            modifier = Modifier.fillParentMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(stringResource(id = R.string.no_items_to_display))
                                        }
                                    }
                                }
                            }
                        }
                        PullRefreshIndicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            refreshing = items.loadState.refresh == LoadState.Loading,
                            state = pullRefreshState,
                            contentColor = MusTuneTheme.colors.primary
                        )
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
        backgroundColor = MusTuneTheme.colors.toolbar,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(5.dp)),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .height(20.dp)
                    .tabIndicatorOffset(tabPositions[currentTab]),
                color = MusTuneTheme.colors.primary
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .padding(3.dp)
                                .size(16.dp),
                            imageVector = tab.icon,
                            contentDescription = "icon",
                            tint = if (selected) MusTuneTheme.colors.primary else MusTuneTheme.colors.textHint
                        )
                        Text(
                            text = tab.name,
                            color = if (selected) MusTuneTheme.colors.primary else MusTuneTheme.colors.textHint,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
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


private fun viewModelObserver(
    viewModel: MusicViewModel,
    context: Context,
    scope: CoroutineScope
) {
    viewModel.effect.onEach {
        when (it) {
            is MusicContract.Effect.ErrorMessage -> Toast.makeText(
                context, it.message, Toast.LENGTH_SHORT
            ).show()
        }
    }.launchIn(scope)
}