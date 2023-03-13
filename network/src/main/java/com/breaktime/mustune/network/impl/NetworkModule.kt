package com.breaktime.mustune.network.impl

import com.breaktime.mustune.common.Constants
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
}