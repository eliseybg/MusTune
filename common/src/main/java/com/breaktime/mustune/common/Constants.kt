package com.breaktime.mustune.common

object Constants {
    const val BASE_URL = "http://10.0.2.2:8080"

    object Pager {
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