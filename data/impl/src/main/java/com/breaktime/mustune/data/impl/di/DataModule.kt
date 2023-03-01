package com.breaktime.mustune.data.impl.di

import com.breaktime.mustune.data.impl.repository.settings.SettingsRepositoryImpl
import com.breaktime.mustune.data.impl.repository.settings.data_source.local.SettingsLocalDataSource
import com.breaktime.mustune.data.api.repository.SettingsRepository
import com.breaktime.mustune.data.impl.repository.settings.data_source.SettingsDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, StorageModule::class])
interface DataModule {
    @Binds
    fun bindSettingsLocalDataSource(local: SettingsLocalDataSource): SettingsDataSource

    @Binds
    fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}