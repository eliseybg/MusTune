package com.breaktime.mustune.session_manager.impl.di

import android.content.Context
import android.content.SharedPreferences
import com.breaktime.mustune.common.Constants
import dagger.Module
import dagger.Provides

@Module
object StorageModule {
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.Settings.PREF_NAME, 0)
    }
}