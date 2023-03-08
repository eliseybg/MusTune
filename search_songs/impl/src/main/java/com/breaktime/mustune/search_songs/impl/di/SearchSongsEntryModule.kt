package com.breaktime.mustune.search_songs.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.search_songs.api.SearchSongsEntry
import com.breaktime.mustune.search_songs.impl.core.SearchSongsImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface SearchSongsEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(SearchSongsEntry::class)
    fun searchSongsEntry(entry: SearchSongsImpl): FeatureEntry
}