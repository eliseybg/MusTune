package com.breaktime.mustune.network.api.extentions

import com.breaktime.mustune.common.exceptions.ServiceException
import retrofit2.Response
import java.lang.Exception

fun <T> Response<T>.retrieveBody(): T {
    handleResponse()
    return body() ?: throw Exception("No body")
}

fun <T> Response<T>.handleResponse() {
    if (!isSuccessful) throw ServiceException(code(), message())
}