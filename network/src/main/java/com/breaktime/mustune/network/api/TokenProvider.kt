package com.breaktime.mustune.network.api

interface TokenProvider {
    fun getToken(): String
}