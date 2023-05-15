package com.breaktime.mustune.search_songs.impl.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.compose.itemsIndexed
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.extentions.isLoading
import com.breaktime.mustune.common.extentions.rememberLazyListState
import com.breaktime.mustune.common.find
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.song.api.SongEntry
import com.breaktime.mustune.song_bottom_sheet.SongBottomSheet
import com.breaktime.mustune.ui_kit.common.ContentBottomSheet
import com.breaktime.mustune.ui_kit.common.ContentBottomSheetValue
import com.breaktime.mustune.ui_kit.common.PrimaryChipButton
import com.breaktime.mustune.ui_kit.common.PrimaryRadioButton
import com.breaktime.mustune.ui_kit.common.PrimaryTextButton
import com.breaktime.mustune.ui_kit.common.Toolbar
import com.breaktime.mustune.ui_kit.common.bottom_sheet.BottomSheetContent
import com.breaktime.mustune.ui_kit.common.rememberContentBottomSheetState
import com.breaktime.mustune.ui_kit.elements.MusicItem
import com.breaktime.mustune.ui_kit.elements.SearchTextField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchSongsScreen(
    viewModel: SearchSongsViewModel,
    navController: NavHostController,
    destinations: Destinations
) {
    val state by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModelObserver(viewModel, context, scope)
    }

    LaunchedEffect(key1 = true) { focusRequester.requestFocus() }

    val filterBottomSheetState = rememberContentBottomSheetState<SearchFilter> {
        if (it is ContentBottomSheetValue.Hidden) keyboardController?.show()
        true
    }
    val songBottomSheetState = rememberContentBottomSheetState<Song> {
        if (it is ContentBottomSheetValue.Hidden) keyboardController?.show()
        true
    }
    ContentBottomSheet(
        state = filterBottomSheetState,
        sheetContent = { filter ->
            SearchBottomSheet(
                filter = filter,
                onApplyClick = { searchFilter ->
                    viewModel.setEvent(SearchSongsContract.Event.UpdateFilter(searchFilter))
                    scope.launch { filterBottomSheetState.hide() }
                    keyboardController?.show()
                }
            )
        }
    ) {
        SongBottomSheet(songBottomSheetState, navController, destinations) {
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
                                hint = stringResource(id = R.string.search),
                                onChangeSearchText = { text ->
                                    viewModel.setEvent(
                                        SearchSongsContract.Event.UpdateSearchText(
                                            text
                                        )
                                    )
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
                                    .clickable {
                                        keyboardController?.hide()
                                        filterBottomSheetState.show(state.searchFilter)
                                    },
                                painter = painterResource(id = R.drawable.ic_sliders_outlined),
                                contentDescription = "filter icon",
                                tint = MusTuneTheme.colors.content
                            )
                        },
                        navigation = {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        keyboardController?.hide()
                                        navController.popBackStack()
                                    },
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back icon",
                                tint = MusTuneTheme.colors.content
                            )
                        }
                    )
                },
                backgroundColor = MusTuneTheme.colors.background
            ) {
                val items = state.songs.collectAsLazyPagingItems()
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
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
                                onMoreClick = {
                                    songBottomSheetState.show(song)
                                    keyboardController?.hide()
                                }
                            )
                            if (index < items.itemSnapshotList.lastIndex)
                                Divider(color = MusTuneTheme.colors.divider, thickness = 1.dp)
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
                                    Text(stringResource(R.string.no_items_to_display))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SearchBottomSheet(
    filter: SearchFilter,
    onApplyClick: (SearchFilter) -> Unit
) {
    val searchInTabs = remember(filter) { filter.searchInTabs.toMutableStateList() }
    var searchInText by remember(filter) { mutableStateOf(filter.searchInText) }
    BottomSheetContent {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = stringResource(R.string.search_in_tabs),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    PrimaryChipButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.explore),
                        selected = searchInTabs.contains(MusicTab.EXPLORE),
                        onSelect = { isSelected ->
                            if (isSelected) searchInTabs.add(MusicTab.EXPLORE)
                            else searchInTabs.remove(MusicTab.EXPLORE)
                        }
                    )
                    PrimaryChipButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.favourite),
                        selected = searchInTabs.contains(MusicTab.FAVOURITE),
                        onSelect = { isSelected ->
                            if (isSelected) searchInTabs.add(MusicTab.FAVOURITE)
                            else searchInTabs.remove(MusicTab.FAVOURITE)
                        }
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    PrimaryChipButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.personal),
                        selected = searchInTabs.contains(MusicTab.PERSONAL),
                        onSelect = { isSelected ->
                            if (isSelected) searchInTabs.add(MusicTab.PERSONAL)
                            else searchInTabs.remove(MusicTab.PERSONAL)
                        }
                    )
                    PrimaryChipButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.shared),
                        selected = searchInTabs.contains(MusicTab.SHARED),
                        onSelect = { isSelected ->
                            if (isSelected) searchInTabs.add(MusicTab.SHARED)
                            else searchInTabs.remove(MusicTab.SHARED)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = stringResource(R.string.search_in_text),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PrimaryRadioButton(
                    text = stringResource(R.string.title_and_artist),
                    checked = searchInText == SearchFilter.SearchInText.TITLE_ARTIST,
                    onCheckedChange = { searchInText = SearchFilter.SearchInText.TITLE_ARTIST }
                )
                PrimaryRadioButton(
                    text = stringResource(R.string.title),
                    checked = searchInText == SearchFilter.SearchInText.TITLE,
                    onCheckedChange = { searchInText = SearchFilter.SearchInText.TITLE }
                )
                PrimaryRadioButton(
                    text = stringResource(R.string.artist),
                    checked = searchInText == SearchFilter.SearchInText.ARTIST,
                    onCheckedChange = { searchInText = SearchFilter.SearchInText.ARTIST }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryTextButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = stringResource(R.string.apply),
                onClick = { onApplyClick(SearchFilter(searchInTabs, searchInText)) }
            )
        }
    }
}

private fun viewModelObserver(
    viewModel: SearchSongsViewModel,
    context: Context,
    scope: CoroutineScope
) {
    viewModel.effect.onEach {
        when (it) {
            is SearchSongsContract.Effect.ErrorMessage -> Toast.makeText(
                context, it.message.asString(context), Toast.LENGTH_SHORT
            ).show()
        }
    }.launchIn(scope)
}