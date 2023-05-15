package com.breaktime.mustune.create_edit_file.impl.presentation

import android.net.Uri
import com.breaktime.mustune.common.UiText
import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState
import com.breaktime.mustune.musicmanager.api.models.ShareSettings

class CreateEditFileContract {
    sealed class Event : UiEvent {
        data class UpdateTitleText(val titleText: String) : Event()
        data class UpdateArtistText(val artistText: String) : Event()
        object OnChangeDownloadableEnabled : Event()
        object OnChangeShareableEnabled : Event()
        object OnDeleteFileClicked : Event()
        data class OnSelectShareType(val shareSettings: ShareSettings.Shared) : Event()
        data class SelectFile(val uri: Uri?) : Event()

        object OnChangeAllowOtherToShare : Event()
        object OnSaveClick : Event()
    }

    data class State(
        val isEdit: Boolean,
        val title: String = "",
        val artist: String = "",
        val isSaveEnabled: Boolean = false,
        val isDownloadable: Boolean = true,
        val shareSettings: ShareSettings = ShareSettings.Shared.AllUsers,
        val attachedFileName: String? = null
    ) : UiState

    sealed class Effect : UiEffect {
        object CloseScreen : Effect()
        object WrongFileFormat : Effect()
        data class ErrorMessage(val message: UiText) : Effect()
    }
}