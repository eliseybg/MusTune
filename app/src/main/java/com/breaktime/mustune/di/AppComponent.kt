package com.breaktime.mustune.di

import com.breaktime.mustune.common.di.CommonProvider
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    dependencies = [CommonProvider::class],
    modules = [NavigationModule::class]
)
interface AppComponent : AppProvider