package com.breaktime.mustune.di

import com.breaktime.mustune.music.impl.di.MusicEntryModule
import com.breaktime.mustune.settings.api.di.SettingsEntryModule
import dagger.Module

@Module(
    includes = [
        SettingsEntryModule::class,
        MusicEntryModule::class,
    ]
)
interface NavigationModule