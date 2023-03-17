package com.breaktime.mustune.login.impl.presentation

import com.breaktime.mustune.common.presentation.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
) : BaseViewModel<LoginContract.Event, LoginContract.State, LoginContract.Effect>() {
    override fun createInitialState() = LoginContract.State()


    override fun handleEvent(event: LoginContract.Event) {

    }
}