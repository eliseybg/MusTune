package com.breaktime.mustune.song.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.song.api.SongEntry
import com.breaktime.mustune.song.impl.core.SongEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface SongEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SongEntry::class)
    fun songEntry(entry: SongEntryImpl): FeatureEntry
}