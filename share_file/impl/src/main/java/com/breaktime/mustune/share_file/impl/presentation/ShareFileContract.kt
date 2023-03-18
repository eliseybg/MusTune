package com.breaktime.mustune.share_file.impl.presentation

import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState

class ShareFileContract {
    sealed class Event : UiEvent {
        data class UpdateSearchText(val searchText: String) : Event()
        object OnSaveClick : Event()
        data class OnRemoveUserClick(val user: SharedUser) : Event()
    }

    data class State(
        val searchText: String = "",
        val sharedUsers: List<SharedUser> = listOf(
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
            SharedUser("email", null),
            SharedUser("email", "username"),
        )
    ) : UiState

    sealed class Effect : UiEffect
}

data class SharedUser(val email: String, val username: String?)
