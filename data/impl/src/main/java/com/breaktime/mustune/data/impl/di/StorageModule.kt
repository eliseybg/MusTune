package com.breaktime.mustune.data.impl.di

import android.content.Context
import android.content.SharedPreferences
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.common.di.FeatureScoped
import dagger.Module
import dagger.Provides

@Module
object StorageModule {
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.Settings.PREF_NAME, 0)
    }
}


