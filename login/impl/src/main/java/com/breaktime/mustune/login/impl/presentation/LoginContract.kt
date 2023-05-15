package com.breaktime.mustune.login.impl.presentation

import com.breaktime.mustune.common.UiText
import com.breaktime.mustune.common.presentation.UiEffect
import com.breaktime.mustune.common.presentation.UiEvent
import com.breaktime.mustune.common.presentation.UiState

class LoginContract {
    sealed class Event : UiEvent {
        object CheckIsAuthorized : Event()
        object OnSignInClick : Event()
        object OnSignUpClick: Event()
        data class UpdateEmailText(val emailText: String) : Event()
        data class UpdateUsernameText(val usernameText: String) : Event()
        data class UpdatePasswordText(val passwordText: String) : Event()
    }

    data class State(
        val email: String = "",
        val username: String = "",
        val password: String = "",
        val isLoading: Boolean = false
    ) : UiState

    sealed class Effect : UiEffect {
        object Authorized : Effect()
        object UnAuthorized : Effect()
        data class ErrorMessage(val message: UiText) : Effect()
    }
}