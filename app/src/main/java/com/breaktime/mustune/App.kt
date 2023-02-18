package com.breaktime.mustune

import android.app.Application
import com.breaktime.mustune.common.di.DaggerCommonComponent
import com.breaktime.mustune.di.AppProvider
import com.breaktime.mustune.di.DaggerAppComponent

class App : Application() {
    lateinit var appProvider: AppProvider
        private set

    override fun onCreate() {
        super.onCreate()

        val commonProvider = DaggerCommonComponent.factory().create(this)
        appProvider = DaggerAppComponent.builder()
            .commonProvider(commonProvider)
            .build()
    }
}

val Application.appProvider: AppProvider
    get() = (this as App).appProvider