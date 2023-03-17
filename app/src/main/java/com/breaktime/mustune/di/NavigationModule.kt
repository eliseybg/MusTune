package com.breaktime.mustune.di

import com.breaktime.mustune.main.impl.di.MainEntryModule
import com.breaktime.mustune.login.impl.di.LoginEntryModule
import com.breaktime.mustune.music.impl.di.MusicEntryModule
import com.breaktime.mustune.search_songs.impl.di.SearchSongsEntryModule
import com.breaktime.mustune.settings.impl.di.SettingsEntryModule
import com.breaktime.mustune.song.impl.di.SongEntryModule
import dagger.Module

@Module(
    includes = [
        LoginEntryModule::class,
        MainEntryModule::class,
        MusicEntryModule::class,
        SongEntryModule::class,
        SearchSongsEntryModule::class,
        SettingsEntryModule::class,
    ]
)
interface NavigationModule