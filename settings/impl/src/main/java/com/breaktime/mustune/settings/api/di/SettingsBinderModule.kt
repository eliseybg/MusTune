package com.breaktime.mustune.settings.api.di

import com.breaktime.mustune.settings.api.data.data_source.SettingsDataSource
import com.breaktime.mustune.settings.api.data.data_source.local.SettingsLocalDataSource
import com.breaktime.mustune.settings.api.data.repository.SettingsRepositoryImpl
import com.breaktime.mustune.settings.api.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module

@Module
interface SettingsBinderModule {
    @Binds
    fun bindSettingsLocalDataSource(local: SettingsLocalDataSource): SettingsDataSource

    @Binds
    fun bindSettingsSettingsRepositoryImpl(impl: SettingsRepositoryImpl): SettingsRepository
}