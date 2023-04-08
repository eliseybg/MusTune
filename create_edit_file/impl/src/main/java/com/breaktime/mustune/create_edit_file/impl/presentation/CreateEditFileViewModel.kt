package com.breaktime.mustune.create_edit_file.impl.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.file_manager.api.FileManager
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.api.models.ShareSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateEditFileViewModel @Inject constructor(
    private val songId: String?,
    private val musicManager: MusicManager,
    private val fileManager: FileManager
) :
    BaseViewModel<CreateEditFileContract.Event, CreateEditFileContract.State, CreateEditFileContract.Effect>() {
    override fun createInitialState(): CreateEditFileContract.State {
        return CreateEditFileContract.State(isEdit = songId != null)
    }

    private val selectedFileUri = MutableStateFlow<Uri?>(null)
    private val title = MutableStateFlow("")
    private val artist = MutableStateFlow("")

    init {
        loadSongInfo()
        combine(title, artist, selectedFileUri) { title, artist, selectedFileUri ->
            val isSaveEnabled = title.isNotEmpty() && artist.isNotEmpty() && selectedFileUri != null
            setState {
                copy(
                    title = title,
                    artist = artist,
                    isSaveEnabled = isSaveEnabled,
                    attachedFileName = selectedFileUri?.let { fileManager.getFileName(it) }
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun loadSongInfo() = viewModelScope.launch {
        songId ?: return@launch
        when (val songOutcome = musicManager.getSong(songId)) {
            is Outcome.Success -> setState {
                val song = songOutcome.value
                copy(
                    title = song.title,
                    artist = song.artist,
                    isDownloadable = song.isDownloadable,
                    shareSettings = song.shareSettings
                )
            }

            is Outcome.Failure<*> -> {}
        }
    }

    override fun handleEvent(event: CreateEditFileContract.Event) {
        when (event) {
            CreateEditFileContract.Event.OnChangeAllowOtherToShare -> setState {
                val onlyInvited = shareSettings as? ShareSettings.Shared.OnlyInvited
                onlyInvited ?: return@setState this
                val isAllowOtherToShare = onlyInvited.allowOtherToShare
                copy(shareSettings = ShareSettings.Shared.OnlyInvited(!isAllowOtherToShare))
            }

            CreateEditFileContract.Event.OnChangeDownloadableEnabled -> setState {
                copy(isDownloadable = !isDownloadable)
            }

            CreateEditFileContract.Event.OnChangeShareableEnabled -> setState {
                val isShared = shareSettings is ShareSettings.Shared
                copy(shareSettings = if (isShared) ShareSettings.NoSharing else ShareSettings.Shared.AllUsers)
            }

            is CreateEditFileContract.Event.OnSelectShareType -> setState { copy(shareSettings = event.shareSettings) }

            CreateEditFileContract.Event.OnSaveClick -> onSave()

            is CreateEditFileContract.Event.UpdateTitleText -> title.value = event.titleText
            is CreateEditFileContract.Event.UpdateArtistText -> artist.value = event.artistText
            is CreateEditFileContract.Event.SelectFile -> selectFile(event.uri)
            is CreateEditFileContract.Event.OnDeleteFileClicked -> deleteFile()
        }
    }

    private fun selectFile(uri: Uri?) = viewModelScope.launch {
        val fileName = uri?.let { fileManager.getFileName(it).substringAfterLast(".") }
        if (fileName in Constants.supportedMusicFormats) selectedFileUri.value = uri
        else setEffect { CreateEditFileContract.Effect.WrongFileFormat }
    }

    private fun deleteFile() = viewModelScope.launch {
        if (uiState.value.isEdit) {
            when (val deleteOutcome = musicManager.deleteSong(songId!!)) {
                is Outcome.Success -> setEffect { CreateEditFileContract.Effect.CloseScreen }
                is Outcome.Failure<*> -> {}
            }
        } else {
            selectedFileUri.value = null
        }
    }

    private fun onSave() = viewModelScope.launch {
        if (uiState.value.isEdit) {
            val editSongOutcome = musicManager.editSong(
                songId!!,
                uiState.value.title,
                uiState.value.artist,
                uiState.value.isDownloadable,
                uiState.value.shareSettings,
            )

            when (editSongOutcome) {
                is Outcome.Success -> setEffect { CreateEditFileContract.Effect.CloseScreen }
                is Outcome.Failure<*> -> {}
            }
        } else {
            val addSongOutcome = musicManager.addSong(
                uiState.value.title,
                uiState.value.artist,
                uiState.value.isDownloadable,
                uiState.value.shareSettings,
                fileManager.getTempFile(selectedFileUri.value!!)!!
            )

            when (addSongOutcome) {
                is Outcome.Success -> setEffect { CreateEditFileContract.Effect.CloseScreen }
                is Outcome.Failure<*> -> {}
            }
        }
    }
}