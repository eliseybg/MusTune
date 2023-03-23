package com.breaktime.mustune.network.impl

import com.breaktime.mustune.common.provider.TokenProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider?
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider?.getToken().orEmpty()
        val builder: Request.Builder = chain.request()
            .newBuilder()
            .addHeader("auth", token)
        return chain.proceed(builder.build())
    }
}