package com.breaktime.mustune.session_manager.impl.di

import com.breaktime.mustune.network.api.TokenProvider
import com.breaktime.mustune.session_manager.api.SessionManager
import com.breaktime.mustune.session_manager.impl.SessionManagerImpl
import com.breaktime.mustune.session_manager.impl.data.source.DataSource
import com.breaktime.mustune.session_manager.impl.data.source.SessionRepositoryImpl
import com.breaktime.mustune.session_manager.impl.data.source.local.LocalDataSource
import com.breaktime.mustune.session_manager.impl.domain.repository.SessionRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, StorageModule::class])
interface SessionManagerModule {
    @Binds
    fun bindDataSource(dataSource: LocalDataSource): DataSource

    @Binds
    fun bindSessionRepository(sessionRepository: SessionRepositoryImpl): SessionRepository

    @Binds
    fun bindSessionManager(sessionManager: SessionManagerImpl): SessionManager

    @Binds
    fun bindTokenProvider(sessionManager: SessionManagerImpl): TokenProvider

}