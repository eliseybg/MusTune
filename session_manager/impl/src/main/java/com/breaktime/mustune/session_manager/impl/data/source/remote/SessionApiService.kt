package com.breaktime.mustune.session_manager.impl.data.source.remote

import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface SessionApiService {
    @POST("login")
    suspend fun login(email: String, password: String): Response<UserInfoEntity>

    @POST("signup")
    suspend fun signup(username: String, email: String, password: String): Response<UserInfoEntity>

    @GET("checkToken")
    suspend fun checkToken(@Header("auth") token: String): Response<UserInfoEntity>
}