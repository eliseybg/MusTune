package com.breaktime.mustune.file_manager.impl.di

import com.breaktime.mustune.file_manager.api.FileManager
import com.breaktime.mustune.file_manager.impl.FileManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface FileManagerModule {
    @Binds
    fun bindFileManager(settingsManager: FileManagerImpl): FileManager
}