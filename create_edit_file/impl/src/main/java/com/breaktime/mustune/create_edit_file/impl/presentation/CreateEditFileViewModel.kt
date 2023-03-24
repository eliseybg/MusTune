package com.breaktime.mustune.create_edit_file.impl.presentation

import com.breaktime.mustune.common.presentation.BaseViewModel
import javax.inject.Inject

class CreateEditFileViewModel @Inject constructor(
    private val songId: String?,
) :
    BaseViewModel<CreateEditFileContract.Event, CreateEditFileContract.State, CreateEditFileContract.Effect>() {
    override fun createInitialState() = CreateEditFileContract.State(isEdit = songId != null)

    override fun handleEvent(event: CreateEditFileContract.Event) {
        when (event) {
            CreateEditFileContract.Event.OnChangeAllowOtherToShare -> setState {
                val onlyInvited = shareState as? ShareState.Shared.OnlyInvited
                onlyInvited ?: return@setState this
                val isAllowOtherToShare = onlyInvited.allowOtherToShare
                copy(shareState = ShareState.Shared.OnlyInvited(!isAllowOtherToShare))
            }

            CreateEditFileContract.Event.OnChangeDownloadableEnabled -> setState {
                copy(isDownloadable = !isDownloadable)
            }

            CreateEditFileContract.Event.OnChangeShareableEnabled -> setState {
                val isShared = shareState is ShareState.Shared
                copy(shareState = if (isShared) ShareState.NoSharing else ShareState.Shared.AllUsers)
            }

            is CreateEditFileContract.Event.OnSelectShareType -> setState { copy(shareState = event.shareState) }

            CreateEditFileContract.Event.OnSaveClick -> TODO()

            is CreateEditFileContract.Event.UpdateArtistText -> setState { copy(artist = event.artistText) }

            is CreateEditFileContract.Event.UpdateTitleText -> setState { copy(title = event.titleText) }
        }
    }
}