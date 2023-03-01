package com.breaktime.mustune.data.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.data.api.DataProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class],
    modules = [DataModule::class]
)
interface DataComponent : DataProvider