package com.breaktime.mustune.data.impl.di

import dagger.Module


@Module
object NetworkModule {

//    @Provides
//    @Singleton
//    fun provideMoshi(): Moshi =
//        Moshi.Builder()
//            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
//            .addLast(KotlinJsonAdapterFactory())
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideMovieDbHttpClient(requestAuthorizer: MovieDbRequestAuthorizer): OkHttpClient =
//        OkHttpClient.Builder()
//            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .addInterceptor(requestAuthorizer)
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideMovieDbApi(client: OkHttpClient, moshi: Moshi): MovieDbApi =
//        Retrofit.Builder()
//            .baseUrl(MovieDbApi.BASE_URL)
//            .client(client)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create()
}