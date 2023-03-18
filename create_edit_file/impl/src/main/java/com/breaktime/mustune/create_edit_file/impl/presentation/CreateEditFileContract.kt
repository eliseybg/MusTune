package com.breaktime.mustune.create_edit_file.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState

class CreateEditFileContract {
    sealed class Event : UiEvent {
        data class UpdateTitleText(val titleText: String) : Event()
        data class UpdateArtistText(val artistText: String) : Event()
        object OnChangeDownloadableEnabled : Event()
        object OnChangeShareableEnabled : Event()
        object OnChangeWhoHasAccess : Event()
        object OnChangeAllowOtherToShare : Event()
        object OnSaveClick : Event()
    }

    data class State(
        val isEdit: Boolean,
        val title: String = "",
        val artist: String = "",
        val isDownloadable: Boolean = true,
        val shareState: ShareState = ShareState.Shared.AllUsers
    ) : UiState

    sealed class Effect : UiEffect
}

sealed class ShareState {
    object NoSharing : ShareState()
    sealed class Shared : ShareState() {
        data class OnlyInvited(val allowOtherToShare: Boolean) : Shared()
        object AnyOneWithLink : Shared()
        object AllUsers : Shared()
    }
}
