package com.breaktime.mustune.musicmanager.impl.di

import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.musicmanager.impl.data.source.songs.remote.SongsApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideSongsApiService(retrofit: Retrofit): SongsApiService =
        retrofit.create(SongsApiService::class.java)
}