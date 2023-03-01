package com.breaktime.mustune.data.impl.di

import dagger.Module


@Module(includes = [NetworkModule::class, StorageModule::class])
interface DataModule {

//    @Binds
//    @Singleton
//    fun mapper(impl: DefaultDataMapper): DataMapper
//
//    @Binds
//    @Singleton
//    fun moviesRepository(impl: DefaultMoviesRepository): MoviesRepository
}