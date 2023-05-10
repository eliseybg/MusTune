package com.breaktime.mustune.musicmanager.impl.data.entities

enum class TabQuery {
    EXPLORE, FAVOURITE, PERSONAL, SHARED, UNKNOWN;

    fun getQuery(): Query = when (this) {
        EXPLORE -> Query.Explore
        FAVOURITE -> Query.Favourite
        SHARED -> Query.Shared
        PERSONAL -> Query.Personal
        else -> Query.Explore
    }

    class Query private constructor(
        val isFavourite: Boolean? = null,
        val isShared: Boolean? = null,
        val isCreator: Boolean? = null
    ) {
        companion object {
            val Explore = Query()
            val Favourite = Query(isFavourite = true)
            val Shared = Query(isShared = true, isCreator = false)
            val Personal = Query(isShared = false, isCreator = true)
        }
    }

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