package com.breaktime.mustune.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.file_manager.api.FileManagerProvider
import dagger.Component
import javax.inject.Singleton
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.session_manager.api.SessionManagerProvider
import com.breaktime.mustune.settings_manager.api.SettingsManagerProvider

@Singleton
@Component(
    dependencies = [
        CommonProvider::class,
        FileManagerProvider::class,
        SessionManagerProvider::class,
        MusicManagerProvider::class,
        SettingsManagerProvider::class],
    modules = [NavigationModule::class]
)
interface AppComponent : AppProvider