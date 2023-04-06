package com.breaktime.mustune.musicmanager.api.models

data class SearchFilter(
    val searchInTabs: List<MusicTab> = emptyList(),
    val searchInText: SearchInText = SearchInText.TITLE_ARTIST
) {
    enum class SearchInText { TITLE_ARTIST, TITLE, ARTIST }
}