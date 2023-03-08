package com.breaktime.mustune.music.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.music.api.MusicEntry
import com.breaktime.mustune.music.impl.core.MusicEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface MusicEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(MusicEntry::class)
    fun musicEntry(entry: MusicEntryImpl): FeatureEntry
}