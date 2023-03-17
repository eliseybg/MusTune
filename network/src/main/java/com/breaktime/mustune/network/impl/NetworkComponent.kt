package com.breaktime.mustune.network.impl

import com.breaktime.mustune.network.api.NetworkProvider
import com.breaktime.mustune.network.api.TokenProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent : NetworkProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun tokenProvider(tokenProvider: TokenProvider?): Builder
        fun build(): NetworkComponent
    }
}