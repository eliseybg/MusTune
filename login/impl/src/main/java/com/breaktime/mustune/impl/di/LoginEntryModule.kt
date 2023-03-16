package com.breaktime.mustune.impl.di

import com.breaktime.mustune.api.LoginEntry
import com.breaktime.mustune.common.FeatureEntry
import com.breaktime.mustune.common.di.FeatureEntryKey
import com.breaktime.mustune.impl.core.LoginEntryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface LoginEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(LoginEntry::class)
    fun loginEntry(entry: LoginEntryImpl): FeatureEntry
}