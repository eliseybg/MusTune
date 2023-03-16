package com.breaktime.mustune.common

import java.util.concurrent.TimeUnit

object Constants {
    const val BACK_PRESS_DEBOUNCE_MILLS = 1200L
    const val SEARCH_DEBOUNCE_MILLS = 200L

    object Retrofit {
        const val TIME_OUT_VALUE = 100L
        val TIME_OUT_UNIT = TimeUnit.SECONDS
        const val BASE_URL = "http://10.0.2.2:8080"
    }

    object Pager {
        const val INITIAL_PAGE = 1
        const val INITIAL_PAGE_SIZE = 40
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 10
    }

    object Settings {
        const val PREF_NAME = "Settings"
        const val KEY_NOTIFICATIONS_ENABLED = "notificationsEnabled"
        const val KEY_DARK_MODE_ENABLED = "darkModeEnabled"
        const val KEY_CURRENT_LANGUAGE = "currentLanguage"
    }
}