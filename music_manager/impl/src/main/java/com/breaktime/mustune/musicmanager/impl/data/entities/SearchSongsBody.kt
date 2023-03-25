package com.breaktime.mustune.musicmanager.impl.data.entities

import com.breaktime.mustune.musicmanager.api.models.SearchFilter

data class SearchSongsBody(
    val searchText: String,
    val searchFilter: SearchFilter,
    val page: Int,
    val pageSize: Int
)