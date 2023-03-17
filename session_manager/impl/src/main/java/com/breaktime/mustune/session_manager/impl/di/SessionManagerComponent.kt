package com.breaktime.mustune.session_manager.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.network.api.NetworkProvider
import com.breaktime.mustune.session_manager.api.SessionManagerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [CommonProvider::class, NetworkProvider::class],
    modules = [SessionManagerModule::class]
)
interface SessionManagerComponent : SessionManagerProvider