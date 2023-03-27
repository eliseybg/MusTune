package com.breaktime.mustune.network.impl

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
interface InterceptorsModule {
    @Binds
    @IntoSet
    fun bindAuthorizationInterceptor(authorizationInterceptor: AuthorizationInterceptor): Interceptor

    @Binds
    @IntoSet
    fun bindConnectivityInterceptor(connectivityInterceptor: ConnectivityInterceptor): Interceptor
}