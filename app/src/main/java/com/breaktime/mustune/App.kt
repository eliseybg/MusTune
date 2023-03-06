package com.breaktime.mustune

import android.app.Application
import com.breaktime.mustune.common.di.DaggerCommonComponent
import com.breaktime.mustune.data.impl.di.DaggerDataComponent
import com.breaktime.mustune.di.AppProvider
import com.breaktime.mustune.di.DaggerAppComponent
import com.breaktime.mustune.musicmanager.impl.di.DaggerMusicManagerComponent

class App : Application() {
    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()

        val commonProvider = DaggerCommonComponent.factory().create(this)
        val dataProvider = DaggerDataComponent.builder().commonProvider(commonProvider).build()
        val musicManagerProvider = DaggerMusicManagerComponent.builder().commonProvider(commonProvider).build()

        appProvider = DaggerAppComponent.builder()
            .commonProvider(commonProvider)
            .dataProvider(dataProvider)
            .musicManagerProvider(musicManagerProvider)
            .build()
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider