package com.breaktime.mustune.network.api

import retrofit2.Retrofit

interface NetworkProvider {
    val retrofit: Retrofit
}