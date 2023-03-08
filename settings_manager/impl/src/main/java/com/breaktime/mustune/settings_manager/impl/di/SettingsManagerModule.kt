package com.breaktime.mustune.settings_manager.impl.di

import com.breaktime.mustune.settings_manager.api.SettingsManager
import com.breaktime.mustune.settings_manager.impl.SettingsManagerImpl
import com.breaktime.mustune.settings_manager.impl.data.source.SettingsRepositoryImpl
import com.breaktime.mustune.settings_manager.impl.domain.SettingsRepository
import dagger.Binds
import dagger.Module

@Module(includes = [StorageModule::class])
interface SettingsManagerModule {
    @Binds
    fun bindSettingsRepository(settingsRepository: SettingsRepositoryImpl): SettingsRepository

    @Binds
    fun bindSettingsManager(settingsManager: SettingsManagerImpl): SettingsManager
}