package com.breaktime.mustune.common

object Constants {
    const val BASE_URL = "http://172.20.10.2:8080"
//const val BASE_URL = "http://10.0.2.2:8080"
    const val BACK_PRESS_DEBOUNCE_MILLS = 1200L
    const val SEARCH_DEBOUNCE_MILLS = 200L

    object Pager {
        const val INITIAL_PAGE = 1
        const val INITIAL_PAGE_SIZE = 40
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 10
    }

    // show be in parser module
    val supportedMusicFormats = arrayOf("gp", "gpx", "gp1", "gp2", "gp3", "gp4", "gp5")
}