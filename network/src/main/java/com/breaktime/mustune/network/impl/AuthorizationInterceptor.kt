package com.breaktime.mustune.network.impl

import com.breaktime.mustune.settings_manager.api.SettingsManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val settingsManager: SettingsManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val token = settingsManager.getToken()
        val token = ""

        val builder: Request.Builder = chain.request()
            .newBuilder()
            .addHeader("auth", token)
        return chain.proceed(builder.build())
    }

}