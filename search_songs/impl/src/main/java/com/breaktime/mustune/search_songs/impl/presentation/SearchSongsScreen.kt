package com.breaktime.mustune.search_songs.impl.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.find
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.song.api.SongEntry
import com.breaktime.mustune.ui_kit.common.Toolbar
import com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet.SongBottomSheet
import com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet.SongBottomSheetContent
import com.breaktime.mustune.ui_kit.elements.MusicItem
import com.breaktime.mustune.ui_kit.elements.SearchTextField

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchSongsScreen(
    viewModel: SearchSongsViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
    val state by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = true) { focusRequester.requestFocus() }

    Scaffold(
        topBar = {
            Toolbar(
                content = {
                    SearchTextField(
                        modifier = Modifier.padding(
                            start = it.calculateStartPadding(LocalLayoutDirection.current) + 10.dp,
                            end = it.calculateEndPadding(LocalLayoutDirection.current) + 10.dp,
                        ),
                        searchText = state.searchText,
                        onChangeSearchText = { text ->
                            viewModel.setEvent(SearchSongsContract.Event.UpdateSearchText(text))
                        },
                        onClearedClick = {
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
                        tint = MusTuneTheme.colors.content
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
        var bottomSheetSong by remember { mutableStateOf<Song?>(null) }
        val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden) {
            if (it == ModalBottomSheetValue.Hidden) bottomSheetSong = null
            true
        }
        LaunchedEffect(key1 = bottomSheetSong) {
            if (bottomSheetSong != null) bottomSheetState.show()
            else bottomSheetState.hide()
        }
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            sheetContent = {
                val bottomSheetContent = SongBottomSheetContent.build {
                    addRow(iconId = R.drawable.ic_favourite, textId = R.string.add_to_favourite) {}
                    addRow(iconId = R.drawable.ic_download, textId = R.string.download_file) {}
                    addRow(iconId = R.drawable.ic_link, textId = R.string.copy_link) {}
                }
                SongBottomSheet(songBottomSheetContent = bottomSheetContent)
            },
        ) {
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
                            artist = item.artist,
                            onItemClick = {
                                val route = destinations.find<SongEntry>().destination(item.id)
                                navController.navigate(route)
                            },
                            onMoreClick = { bottomSheetSong = item }
                        )
                        if (index < items.itemSnapshotList.lastIndex)
                            Divider(color = MusTuneTheme.colors.divider, thickness = 1.dp)
                    }
                }
            }
        }
    }
}