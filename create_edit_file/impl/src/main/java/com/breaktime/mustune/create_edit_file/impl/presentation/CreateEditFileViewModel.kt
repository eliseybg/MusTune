package com.breaktime.mustune.create_edit_file.impl.presentation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.domain.ErrorCode
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
            val isFieldsNotEmpty = title.isNotEmpty()
            val isFileSelected = selectedFileUri != null
            setState {
                copy(
                    title = title,
                    artist = artist,
                    isSaveEnabled = isFieldsNotEmpty && (isFileSelected || isEdit),
                    attachedFileName = selectedFileUri?.let { fileManager.getFileName(it) }
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun loadSongInfo() = viewModelScope.launch {
        songId ?: return@launch
        when (val outcome = musicManager.getSong(songId)) {
            is Outcome.Success -> {
                val song = outcome.value
                title.value = song.title
                artist.value = song.artist
                setState {
                    copy(isDownloadable = song.isDownloadable, shareSettings = song.shareSettings)
                }
            }

            is Outcome.Failure -> {
                when (outcome) {
                    Outcome.Failure.Connection -> TODO()
                    is Outcome.Failure.Unknown -> TODO()
                    is Outcome.Failure.ServiceError -> {
                        when (outcome.code) {
                            ErrorCode.BAD_REQUEST -> TODO()
                            ErrorCode.UNAUTHORIZED -> TODO()
                            ErrorCode.NOT_FOUND -> TODO()
                            ErrorCode.INTERNAL_SERVER_ERROR -> TODO()
                            ErrorCode.Unknown -> TODO()
                        }
                    }
                }
            }
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
            when (val outcome = musicManager.deleteSong(songId!!)) {
                is Outcome.Success -> setEffect { CreateEditFileContract.Effect.CloseScreen }
                is Outcome.Failure -> {
                    when (outcome) {
                        Outcome.Failure.Connection -> TODO()
                        is Outcome.Failure.Unknown -> TODO()
                        is Outcome.Failure.ServiceError -> {
                            when (outcome.code) {
                                ErrorCode.BAD_REQUEST -> TODO()
                                ErrorCode.UNAUTHORIZED -> TODO()
                                ErrorCode.NOT_FOUND -> TODO()
                                ErrorCode.INTERNAL_SERVER_ERROR -> TODO()
                                ErrorCode.Unknown -> TODO()
                            }
                        }
                    }
                }
            }
        } else {
            selectedFileUri.value = null
        }
    }

    private fun onSave() = viewModelScope.launch {
        val outcome = if (uiState.value.isEdit) musicManager.editSong(
            songId!!,
            uiState.value.title,
            uiState.value.artist,
            uiState.value.isDownloadable,
            uiState.value.shareSettings,
        )
        else musicManager.addSong(
            uiState.value.title,
            uiState.value.artist,
            uiState.value.isDownloadable,
            uiState.value.shareSettings,
            selectedFileUri.value ?: return@launch
        )

        when (outcome) {
            is Outcome.Success -> setEffect { CreateEditFileContract.Effect.CloseScreen }
            is Outcome.Failure -> {
                when (outcome) {
                    Outcome.Failure.Connection -> TODO()
                    is Outcome.Failure.Unknown -> TODO()
                    is Outcome.Failure.ServiceError -> {
                        when (outcome.code) {
                            ErrorCode.BAD_REQUEST -> TODO()
                            ErrorCode.UNAUTHORIZED -> TODO()
                            ErrorCode.NOT_FOUND -> TODO()
                            ErrorCode.INTERNAL_SERVER_ERROR -> TODO()
                            ErrorCode.Unknown -> TODO()
                        }
                    }
                }
            }
        }
    }
}