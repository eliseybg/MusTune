package com.breaktime.mustune.data.api

import androidx.compose.runtime.compositionLocalOf


interface DataProvider {

//    val moviesRepository: MoviesRepository
}

val LocalDataProvider = compositionLocalOf<DataProvider> { error("No data provider found!") }