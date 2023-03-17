package com.breaktime.mustune.settings_manager.impl.di

import android.content.Context
import android.content.SharedPreferences
import com.breaktime.mustune.settings_manager.impl.util.Constants
import dagger.Module
import dagger.Provides

@Module
object StorageModule {
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREF_NAME, 0)
    }
}