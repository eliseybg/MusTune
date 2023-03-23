package com.breaktime.mustune.login.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.session_manager.api.SessionManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {
    override fun createInitialState() = LoginContract.State()

    override fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnSignInClick -> login()
            is LoginContract.Event.OnSignUpClick -> register()

            LoginContract.Event.CheckIsAuthorized -> checkIsAuthorized()
            is LoginContract.Event.UpdateEmailText -> setState { copy(email = event.emailText) }
            is LoginContract.Event.UpdatePasswordText -> setState { copy(password = event.passwordText) }
            is LoginContract.Event.UpdateUsernameText -> setState { copy(username = event.usernameText) }
        }
    }

    private fun login() = viewModelScope.launch {
        setState { copy(isLoading = true) }
        when (val loginResult = sessionManager.login(uiState.value.email, uiState.value.password)) {
            is Outcome.Success -> setEffect { LoginContract.Effect.Authorized }
            is Outcome.Failure<*> -> {
                setState { copy(isLoading = false) }
                setEffect { LoginContract.Effect.ErrorMessage(loginResult.e.message.orEmpty()) }
            }
        }
    }

    private fun register() = viewModelScope.launch {
        setState { copy(isLoading = true) }
        when (val loginResult = sessionManager.register(
            uiState.value.email,
            uiState.value.username,
            uiState.value.password
        )) {
            is Outcome.Success -> setEffect { LoginContract.Effect.Authorized }
            is Outcome.Failure<*> -> {
                setState { copy(isLoading = false) }
                setEffect { LoginContract.Effect.ErrorMessage(loginResult.e.message.orEmpty()) }
            }
        }
    }

    private fun checkIsAuthorized() = viewModelScope.launch {
        when (val isAuthorized = sessionManager.isAuthorized()) {
            is Outcome.Success -> if (isAuthorized.value) setEffect { LoginContract.Effect.Authorized }
            else setEffect { LoginContract.Effect.UnAuthorized }

            is Outcome.Failure<*> -> setEffect {
                LoginContract.Effect.ErrorMessage(isAuthorized.e.message.orEmpty())
            }
        }
    }
}