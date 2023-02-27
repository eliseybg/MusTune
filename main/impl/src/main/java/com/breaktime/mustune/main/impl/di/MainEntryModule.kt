package com.breaktime.mustune.main.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.main.api.MainEntry
import com.breaktime.mustune.main.impl.core.MainEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface MainEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(MainEntry::class)
    fun mainEntry(entry: MainEntryImpl): FeatureEntry
}