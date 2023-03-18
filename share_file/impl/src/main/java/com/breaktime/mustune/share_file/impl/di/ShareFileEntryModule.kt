package com.breaktime.mustune.share_file.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.share_file.api.ShareFileEntry
import com.breaktime.mustune.share_file.impl.core.ShareFileEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ShareFileEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ShareFileEntry::class)
    fun shareFileEntry(entry: ShareFileEntryImpl): FeatureEntry
}