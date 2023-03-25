package com.breaktime.mustune.musicmanager.api.models

data class SearchFilter(
    val searchInTabs: List<SearchInTab> = emptyList(),
    val searchInText: SearchInText = SearchInText.TITLE_ARTIST
) {
    enum class SearchInTab { EXPLORE, FAVOURITE, PERSONAL, SHARED }
    enum class SearchInText { TITLE_ARTIST, TITLE, ARTIST }
}