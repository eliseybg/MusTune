package com.breaktime.mustune.create_edit_file.impl.di

import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.create_edit_file.api.CreateEditFileEntry
import com.breaktime.mustune.create_edit_file.impl.core.CreateEditFileEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface CreateEditFileEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(CreateEditFileEntry::class)
    fun createEditFileEntry(entry: CreateEditFileEntryImpl): FeatureEntry
}