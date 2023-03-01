package com.breaktime.mustune.di

import com.breaktime.mustune.common.di.CommonProvider
import dagger.Component
import javax.inject.Singleton
import com.breaktime.mustune.data.api.DataProvider

@Singleton
@Component(
    dependencies = [CommonProvider::class, DataProvider::class],
    modules = [NavigationModule::class]
)
interface AppComponent : AppProvider