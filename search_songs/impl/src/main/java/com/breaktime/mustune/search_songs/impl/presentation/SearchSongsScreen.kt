package com.breaktime.mustune.search_songs.impl.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.breaktime.mustune.common.Destinations
import com.breaktime.mustune.common.extentions.isLoading
import com.breaktime.mustune.common.find
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.api.models.SearchFilter
import com.breaktime.mustune.musicmanager.api.models.Song
import com.breaktime.mustune.resources.R
import com.breaktime.mustune.resources.theme.MusTuneTheme
import com.breaktime.mustune.song.api.SongEntry
import com.breaktime.mustune.ui_kit.common.PrimaryChipButton
import com.breaktime.mustune.ui_kit.common.PrimaryRadioButton
import com.breaktime.mustune.ui_kit.common.PrimaryTextButton
import com.breaktime.mustune.ui_kit.common.Toolbar
import com.breaktime.mustune.ui_kit.common.bottom_sheet.BottomSheetContent
import com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet.SongBottomSheet
import com.breaktime.mustune.ui_kit.common.bottom_sheet.song_bottom_sheet.SongBottomSheetContent
import com.breaktime.mustune.ui_kit.elements.MusicItem
import com.breaktime.mustune.ui_kit.elements.SearchTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
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

    LaunchedEffect(key1 = true) { focusRequester.requestFocus() }

    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden) {
        if (it == ModalBottomSheetValue.Hidden) keyboardController?.show()
        true
    }

    ModalBottomSheetLayout(
        sheetContent = {
            SearchBottomSheet(
                isVisible = bottomSheetState.isVisible,
                filter = state.searchFilter,
                onApplyClick = { searchFilter ->
                    viewModel.setEvent(SearchSongsContract.Event.UpdateFilter(searchFilter))
                    scope.launch { bottomSheetState.hide() }
                    keyboardController?.show()
                }
            )
        },
        sheetState = bottomSheetState
    ) {
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
                                .clickable {
                                    keyboardController?.hide()
                                    scope.launch { bottomSheetState.show() }
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
                        addRow(
                            iconId = R.drawable.ic_favourite,
                            textId = R.string.add_to_favourite
                        ) {}
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
                    itemsIndexed(items) { index, song ->
                        song?.let {
                            MusicItem(
                                title = song.title,
                                artist = song.artist,
                                onItemClick = {
                                    val route =
                                        destinations.find<SongEntry>().destination(song.id)
                                    navController.navigate(route)
                                },
                                onMoreClick = { bottomSheetSong = song }
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
    isVisible: Boolean,
    filter: SearchFilter,
    onApplyClick: (SearchFilter) -> Unit
) {
    val searchInTabs = remember(isVisible) { filter.searchInTabs.toMutableStateList() }
    var searchInText by remember(isVisible) { mutableStateOf(filter.searchInText) }
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