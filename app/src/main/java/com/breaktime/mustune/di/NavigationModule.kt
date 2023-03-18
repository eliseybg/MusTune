package com.breaktime.mustune.di

import com.breaktime.mustune.create_edit_file.impl.di.CreateEditFileEntryModule
import com.breaktime.mustune.main.impl.di.MainEntryModule
import com.breaktime.mustune.login.impl.di.LoginEntryModule
import com.breaktime.mustune.music.impl.di.MusicEntryModule
import com.breaktime.mustune.search_songs.impl.di.SearchSongsEntryModule
import com.breaktime.mustune.settings.impl.di.SettingsEntryModule
import com.breaktime.mustune.share_file.impl.di.ShareFileEntryModule
import com.breaktime.mustune.song.impl.di.SongEntryModule
import dagger.Module

@Module(
    includes = [
        LoginEntryModule::class,
        MainEntryModule::class,
        MusicEntryModule::class,
        SettingsEntryModule::class,
        SongEntryModule::class,
        SearchSongsEntryModule::class,
        ShareFileEntryModule::class,
        CreateEditFileEntryModule::class
    ]
)
interface NavigationModule