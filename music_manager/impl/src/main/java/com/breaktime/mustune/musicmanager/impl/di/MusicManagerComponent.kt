package com.breaktime.mustune.musicmanager.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class],
    modules = [MusicManagerModule::class]
)
interface MusicManagerComponent : MusicManagerProvider