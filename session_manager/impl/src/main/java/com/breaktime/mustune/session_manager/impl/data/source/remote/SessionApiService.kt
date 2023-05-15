package com.breaktime.mustune.session_manager.impl.data.source.remote

import com.breaktime.mustune.session_manager.impl.data.entities.LoginBody
import com.breaktime.mustune.session_manager.impl.data.entities.SignupBody
import com.breaktime.mustune.session_manager.impl.data.entities.UserInfoEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface SessionApiService {
    @POST("login")
    suspend fun login(@Body body: LoginBody): Response<UserInfoEntity>

    @POST("signup")
    suspend fun signup(@Body body: SignupBody): Response<UserInfoEntity>

    @DELETE("deleteAccount")
    suspend fun deleteAccount(@Header("Authorization") token: String): Response<Unit>
}