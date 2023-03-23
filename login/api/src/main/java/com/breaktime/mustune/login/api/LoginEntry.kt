package com.breaktime.mustune.login.api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.breaktime.mustune.common.AggregateFeatureEntry

abstract class LoginEntry : AggregateFeatureEntry {
    final override val featureRoute = "login"
    protected open var rootScreen by mutableStateOf(LoginScreen.SPLASH)

    fun destination(screen: LoginScreen = LoginScreen.SPLASH) =
        featureRoute.also { rootScreen = screen }

    enum class LoginScreen { SPLASH, ONBOARDING, SIGN_IN, SIGN_UP }
}