package com.breaktime.mustune.settings.api.di

import android.content.Context
import android.content.SharedPreferences
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.di.FeatureScoped
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SettingsProviderModule {
    @Provides
    @FeatureScoped
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.Settings.PREF_NAME, 0)
    }
}