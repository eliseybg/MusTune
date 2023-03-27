package com.breaktime.mustune.network.impl

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.network.api.NetworkProvider
import com.breaktime.mustune.common.provider.TokenProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class],
    dependencies = [CommonProvider::class]
)
interface NetworkComponent : NetworkProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun tokenProvider(tokenProvider: TokenProvider?): Builder
        fun commonProvider(commonProvider: CommonProvider): Builder
        fun build(): NetworkComponent
    }
}