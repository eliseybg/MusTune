package com.breaktime.mustune.session_manager.impl.di

import com.breaktime.mustune.session_manager.impl.data.source.remote.SessionApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideSessionApiService(retrofit: Retrofit): SessionApiService =
        retrofit.create(SessionApiService::class.java)
}