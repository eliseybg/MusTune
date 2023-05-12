package com.breaktime.mustune.song_bottom_sheet

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.Song
import kotlinx.coroutines.launch
import javax.inject.Inject

class SongBottomSheetViewModel @Inject constructor(
    private val musicManager: MusicManager,
    private val clipboardUtil: ClipboardUtil,
    private val downloadUtil: DownloadUtil
) :
    BaseViewModel<SongBottomSheetContract.Event, SongBottomSheetContract.State, SongBottomSheetContract.Effect>() {
    override fun createInitialState() = SongBottomSheetContract.State()
    override fun handleEvent(event: SongBottomSheetContract.Event) {
        when (event) {
            is SongBottomSheetContract.Event.OnEditInfoClicked -> openEditInfo(event.song)
            is SongBottomSheetContract.Event.OnAddFavouriteClicked -> addToFavourite(event.song)
            is SongBottomSheetContract.Event.OnRemoveFavouriteClicked -> removeFromFavourite(event.song)
            is SongBottomSheetContract.Event.OnDownloadFileClicked -> downloadFile(event.song)
            is SongBottomSheetContract.Event.OnShareSettingsClicked -> openShareSettings(event.song)
            is SongBottomSheetContract.Event.OnCopyLinkClicked -> copyLinkToBuffer(event.song)
            is SongBottomSheetContract.Event.OnDeleteFileClicked -> deleteFile(event.song)
        }
    }

    private fun openEditInfo(song: Song) = viewModelScope.launch {
        setEffect { SongBottomSheetContract.Effect.OpenEditScreen(song.id) }
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }

    private fun addToFavourite(song: Song) = viewModelScope.launch {
        musicManager.addToFavourite(song.id)
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }

    private fun removeFromFavourite(song: Song) = viewModelScope.launch {
        musicManager.removeFromFavourite(song.id)
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }

    private fun downloadFile(song: Song) = viewModelScope.launch {
        downloadUtil.downloadSong(song.id, song.title, song.artist)
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }

    private fun openShareSettings(song: Song) = viewModelScope.launch {
        setEffect { SongBottomSheetContract.Effect.OpenShareScreen(song.id) }
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }

    private fun copyLinkToBuffer(song: Song) = viewModelScope.launch {
        clipboardUtil.copyToClipboard(song.id)
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }

    private fun deleteFile(song: Song) = viewModelScope.launch {
        musicManager.deleteSong(song.id)
    }.invokeOnCompletion { setEffect { SongBottomSheetContract.Effect.CloseBottomSheet } }
}