package com.breaktime.mustune.login.impl.di

import com.breaktime.mustune.login.api.LoginProvider
import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.login.impl.presentation.LoginViewModel
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [CommonProvider::class],
    modules = [LoginBinderModule::class, LoginProviderModule::class]
)
interface LoginComponent : LoginProvider, CommonProvider {
    val viewModel: LoginViewModel
}