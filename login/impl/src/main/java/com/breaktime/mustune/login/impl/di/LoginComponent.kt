package com.breaktime.mustune.login.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.login.api.LoginProvider
import com.breaktime.mustune.login.impl.presentation.LoginViewModel
import com.breaktime.mustune.session_manager.api.SessionManagerProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [SessionManagerProvider::class],
    modules = [LoginBinderModule::class, LoginProviderModule::class]
)
interface LoginComponent : LoginProvider, SessionManagerProvider {
    val viewModel: LoginViewModel
}