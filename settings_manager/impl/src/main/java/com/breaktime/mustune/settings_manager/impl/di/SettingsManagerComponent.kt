package com.breaktime.mustune.settings_manager.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.settings_manager.api.SettingsManagerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class],
    modules = [SettingsManagerModule::class]
)
interface SettingsManagerComponent : SettingsManagerProvider