package com.breaktime.mustune.musicmanager.impl.data.entities

enum class TabQuery {
    EXPLORE, FAVOURITE, PERSONAL, SHARED, UNKNOWN;

    companion object {
        fun fromString(value: String): TabQuery = when (value) {
            EXPLORE.name -> EXPLORE
            FAVOURITE.name -> FAVOURITE
            SHARED.name -> SHARED
            PERSONAL.name -> PERSONAL
            else -> UNKNOWN
        }
    }
}