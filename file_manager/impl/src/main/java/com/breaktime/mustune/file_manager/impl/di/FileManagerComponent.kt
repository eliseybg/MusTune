package com.breaktime.mustune.file_manager.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.file_manager.api.FileManagerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class],
    modules = [FileManagerModule::class]
)
interface FileManagerComponent : FileManagerProvider