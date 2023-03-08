package com.breaktime.mustune.di

import com.breaktime.mustune.main.impl.di.MainEntryModule
import com.breaktime.mustune.music.impl.di.MusicEntryModule
import com.breaktime.mustune.search_songs.impl.di.SearchSongsEntryModule
import com.breaktime.mustune.settings.impl.di.SettingsEntryModule
import dagger.Module

@Module(
    includes = [
        MainEntryModule::class,
        MusicEntryModule::class,
        SearchSongsEntryModule::class,
        SettingsEntryModule::class,
    ]
)
interface NavigationModule