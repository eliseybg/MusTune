package com.breaktime.mustune.network.impl

import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.network.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(Constants.Retrofit.TIME_OUT_VALUE, Constants.Retrofit.TIME_OUT_UNIT)
            .connectTimeout(Constants.Retrofit.TIME_OUT_VALUE, Constants.Retrofit.TIME_OUT_UNIT)
            .writeTimeout(Constants.Retrofit.TIME_OUT_VALUE, Constants.Retrofit.TIME_OUT_UNIT)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.Retrofit.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}