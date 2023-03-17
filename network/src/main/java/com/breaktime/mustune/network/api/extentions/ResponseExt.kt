package com.breaktime.mustune.network.api.extentions

import retrofit2.Response
import java.lang.Exception

fun <T> Response<T>.retrieveBody(): T {
    handleResponse()
    return body() ?: throw Exception()
}

fun <T> Response<T>.handleResponse() {
    if (!isSuccessful) throw Exception()
}