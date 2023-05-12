package com.breaktime.mustune

import android.app.Application
import com.breaktime.mustune.common.di.DaggerCommonComponent
import com.breaktime.mustune.di.AppProvider
import com.breaktime.mustune.di.DaggerAppComponent
import com.breaktime.mustune.file_manager.impl.di.DaggerFileManagerComponent
import com.breaktime.mustune.musicmanager.impl.di.DaggerMusicManagerComponent
import com.breaktime.mustune.network.impl.DaggerNetworkComponent
import com.breaktime.mustune.session_manager.impl.di.DaggerSessionManagerComponent
import com.breaktime.mustune.settings_manager.impl.di.DaggerSettingsManagerComponent

class App : Application() {
    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()
        val commonProvider = DaggerCommonComponent.factory().create(this)

        val fileManagerProvider = DaggerFileManagerComponent.builder()
            .commonProvider(commonProvider)
            .build()

        val settingsManagerProvider = DaggerSettingsManagerComponent.builder()
            .commonProvider(commonProvider)
            .build()

        val sessionManagerProvider = DaggerSessionManagerComponent.builder()
            .commonProvider(commonProvider)
            .networkProvider(
                DaggerNetworkComponent.builder().commonProvider(commonProvider).build()
            )
            .build()

        val networkProvider = DaggerNetworkComponent.builder()
            .tokenProvider(sessionManagerProvider.tokenProvider)
            .commonProvider(commonProvider)
            .build()

        val musicManagerProvider = DaggerMusicManagerComponent.builder()
            .commonProvider(commonProvider)
            .networkProvider(networkProvider)
            .fileManagerProvider(fileManagerProvider)
            .build()

        appProvider = DaggerAppComponent.builder()
            .commonProvider(commonProvider)
            .fileManagerProvider(fileManagerProvider)
            .sessionManagerProvider(sessionManagerProvider)
            .musicManagerProvider(musicManagerProvider)
            .settingsManagerProvider(settingsManagerProvider)
            .build()
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider