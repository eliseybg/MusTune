package com.breaktime.mustune.musicmanager.impl.di

import com.breaktime.mustune.musicmanager.impl.data.source.songs.remote.SongsApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideSongsApiService(retrofit: Retrofit): SongsApiService =
        retrofit.create(SongsApiService::class.java)
}