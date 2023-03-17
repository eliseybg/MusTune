package com.breaktime.mustune.login.api

import com.breaktime.mustune.common.ComposableFeatureEntry

abstract class LoginEntry : ComposableFeatureEntry {
    final override val featureRoute = "login"

    fun destination() = featureRoute
}