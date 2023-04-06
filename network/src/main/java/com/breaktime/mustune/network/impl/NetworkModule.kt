package com.breaktime.mustune.network.impl

import com.breaktime.mustune.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [InterceptorsModule::class])
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(Constants.TIME_OUT_VALUE, Constants.TIME_OUT_UNIT)
            .connectTimeout(Constants.TIME_OUT_VALUE, Constants.TIME_OUT_UNIT)
            .writeTimeout(Constants.TIME_OUT_VALUE, Constants.TIME_OUT_UNIT)
        interceptors.forEach { builder.addInterceptor(it) }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @IntoSet
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
}