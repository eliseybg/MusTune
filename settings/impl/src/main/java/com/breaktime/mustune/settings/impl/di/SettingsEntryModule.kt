package com.breaktime.mustune.settings.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.settings.api.SettingsEntry
import com.breaktime.mustune.settings.impl.core.SettingsEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface SettingsEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SettingsEntry::class)
    fun settingsEntry(entry: SettingsEntryImpl): FeatureEntry
}