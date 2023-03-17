package com.breaktime.mustune.settings.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.settings.api.SettingsProvider
import com.breaktime.mustune.settings.impl.presentation.SettingsViewModel
import com.breaktime.mustune.settings_manager.api.SettingsManagerProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [SettingsManagerProvider::class]
)
interface SettingsComponent : SettingsProvider, SettingsManagerProvider {
    val viewModel: SettingsViewModel
}