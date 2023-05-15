package com.breaktime.mustune.login.impl.presentation

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.UiText
import com.breaktime.mustune.common.domain.ErrorCode
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.resources.R
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
        if (!uiState.value.email.isEmailValid() && !uiState.value.email.isUsernameValid()) {
            setEffect {
                LoginContract.Effect.ErrorMessage(UiText.StringResource(R.string.wrong_username_or_email))
            }
            return@launch
        }
        setState { copy(isLoading = true) }
        when (val outcome = sessionManager.login(uiState.value.email, uiState.value.password)) {
            is Outcome.Success -> setEffect { LoginContract.Effect.Authorized }
            is Outcome.Failure -> {
                setState { copy(isLoading = false) }
                when (outcome) {
                    Outcome.Failure.Connection -> setEffect {
                        LoginContract.Effect.ErrorMessage(
                            UiText.StringResource(R.string.no_internet)
                        )
                    }

                    is Outcome.Failure.Unknown -> setEffect {
                        LoginContract.Effect.ErrorMessage(
                            UiText.StringResource(R.string.unknown_exception)
                        )
                    }

                    is Outcome.Failure.ServiceError -> when (outcome.code) {
                        ErrorCode.BAD_REQUEST -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.bad_request)
                            )
                        }

                        ErrorCode.UNAUTHORIZED -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.unauthorized)
                            )
                        }

                        ErrorCode.NOT_FOUND -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.no_user)
                            )
                        }

                        ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.Unknown -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.unknown_exception)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun register() = viewModelScope.launch {
        if (!uiState.value.username.isUsernameValid()) {
            setEffect {
                LoginContract.Effect.ErrorMessage(UiText.StringResource(R.string.wrong_username_format))
            }
            return@launch
        }
        if (!uiState.value.email.isEmailValid()) {
            setEffect {
                LoginContract.Effect.ErrorMessage(UiText.StringResource(R.string.wrong_email_format))
            }
            return@launch
        }
        setState { copy(isLoading = true) }
        when (val outcome = sessionManager.register(
            uiState.value.username,
            uiState.value.email,
            uiState.value.password
        )) {
            is Outcome.Success -> setEffect { LoginContract.Effect.Authorized }
            is Outcome.Failure -> {
                setState { copy(isLoading = false) }
                when (outcome) {
                    Outcome.Failure.Connection -> setEffect {
                        LoginContract.Effect.ErrorMessage(
                            UiText.StringResource(R.string.no_internet)
                        )
                    }

                    is Outcome.Failure.Unknown -> setEffect {
                        LoginContract.Effect.ErrorMessage(
                            UiText.StringResource(R.string.unknown_exception)
                        )
                    }

                    is Outcome.Failure.ServiceError -> when (outcome.code) {
                        ErrorCode.BAD_REQUEST -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.username_taken)
                            )
                        }

                        ErrorCode.UNAUTHORIZED -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.unauthorized)
                            )
                        }

                        ErrorCode.NOT_FOUND -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.no_user)
                            )
                        }

                        ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.Unknown -> setEffect {
                            LoginContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.unknown_exception)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun checkIsAuthorized() = viewModelScope.launch {
        val isAuthorized = sessionManager.isAuthorized()
        if (isAuthorized) setEffect { LoginContract.Effect.Authorized }
        setEffect { LoginContract.Effect.UnAuthorized }
    }
}

private fun String.isEmailValid(): Boolean = matches(Patterns.EMAIL_ADDRESS.toRegex())
private fun String.isUsernameValid(): Boolean = matches(Regex("[A-Za-z0-9]+"))
