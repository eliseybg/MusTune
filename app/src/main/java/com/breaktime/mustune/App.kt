package com.breaktime.mustune

import android.app.Application
import com.breaktime.mustune.common.di.DaggerCommonComponent
import com.breaktime.mustune.di.AppProvider
import com.breaktime.mustune.di.DaggerAppComponent
import com.breaktime.mustune.musicmanager.impl.di.DaggerMusicManagerComponent
import com.breaktime.mustune.network.impl.DaggerNetworkComponent
import com.breaktime.mustune.settings_manager.impl.di.DaggerSettingsManagerComponent

class App : Application() {
    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()

        val commonProvider = DaggerCommonComponent.factory().create(this)

        val settingsManagerProvider = DaggerSettingsManagerComponent.builder()
            .commonProvider(commonProvider)
            .build()

        val networkProvider = DaggerNetworkComponent.builder().build()

        val musicManagerProvider = DaggerMusicManagerComponent.builder()
            .commonProvider(commonProvider)
            .networkProvider(networkProvider)
            .build()

        appProvider = DaggerAppComponent.builder()
            .commonProvider(commonProvider)
            .musicManagerProvider(musicManagerProvider)
            .settingsManagerProvider(settingsManagerProvider)
            .build()
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider