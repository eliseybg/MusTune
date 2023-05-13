package com.breaktime.mustune.settings.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.session_manager.api.SessionManagerProvider
import com.breaktime.mustune.settings.api.SettingsProvider
import com.breaktime.mustune.settings.impl.presentation.SettingsViewModel
import com.breaktime.mustune.settings_manager.api.SettingsManagerProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [SettingsManagerProvider::class, MusicManagerProvider::class, SessionManagerProvider::class]
)
interface SettingsComponent : SettingsProvider, SettingsManagerProvider {
    val viewModel: SettingsViewModel
}