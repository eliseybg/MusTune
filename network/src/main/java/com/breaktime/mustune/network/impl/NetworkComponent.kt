package com.breaktime.mustune.network.impl

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.network.api.NetworkProvider
import com.breaktime.mustune.settings_manager.api.SettingsManagerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class, SettingsManagerProvider::class],
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider, SettingsManagerProvider