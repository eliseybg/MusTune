package com.breaktime.mustune.settings.api.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.data.api.DataProvider
import com.breaktime.mustune.settings.api.SettingsProvider
import com.breaktime.mustune.settings.api.presentation.SettingsViewModel
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [CommonProvider::class, DataProvider::class],
    modules = [SettingsBinderModule::class, SettingsProviderModule::class]
)
interface SettingsComponent : SettingsProvider, CommonProvider {
    val viewModel: SettingsViewModel
}