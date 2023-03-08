package com.breaktime.mustune.search_songs.impl.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.breaktime.common.R
import com.breaktime.mustune.common.composable.MusicItem
import com.breaktime.mustune.common.composable.Toolbar
import com.breaktime.mustune.search_songs.impl.presentation.components.SearchField

@Composable
fun SearchSongsScreen(viewModel: SearchSongsViewModel, navController: NavHostController) {
    val state by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = true) { focusRequester.requestFocus() }

    Scaffold(
        topBar = {
            Toolbar(
                content = {
                    SearchField(
                        modifier = Modifier.padding(
                            start = it.calculateStartPadding(LocalLayoutDirection.current) + 10.dp,
                            end = it.calculateEndPadding(LocalLayoutDirection.current) + 10.dp,
                        ),
                        searchText = state.searchText,
                        onChangeSearchText = { text ->
                            viewModel.setEvent(SearchSongsContract.Event.UpdateSearchText(text))
                        },
                        clearSearchText = {
                            viewModel.setEvent(SearchSongsContract.Event.UpdateSearchText(""))
                        },
                        focusRequester = focusRequester
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { viewModel.setEvent(SearchSongsContract.Event.OnFilterClicked) },
                        painter = painterResource(id = R.drawable.ic_sliders_outlined),
                        contentDescription = "filter icon",
                        tint = Color.Black
                    )
                },
                navigation = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { navController.popBackStack() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back icon",
                    )
                }
            )
        }
    ) {
        val items = state.songs.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
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