package com.breaktime.mustune.share_file.impl.presentation

import com.breaktime.mustune.common.presentation.BaseViewModel
import javax.inject.Inject

class ShareFileViewModel @Inject constructor(
) : BaseViewModel<ShareFileContract.Event, ShareFileContract.State, ShareFileContract.Effect>() {
    override fun createInitialState() = ShareFileContract.State()

    override fun handleEvent(event: ShareFileContract.Event) {
        when (event) {
            is ShareFileContract.Event.OnRemoveUserClick -> removeUser(event.user)
            is ShareFileContract.Event.OnSaveClick -> save()
            is ShareFileContract.Event.UpdateSearchText -> setState { copy(searchText = event.searchText) }
        }
    }

    private fun removeUser(user: SharedUser) {

    }

    private fun save() {

    }
}