package com.breaktime.mustune.musicmanager.api.models

data class SearchFilter(
    val searchInTabs: List<MusicTab> = emptyList(),
    val searchInText: SearchInText = SearchInText.TITLE_ARTIST
) {
    enum class SearchInText { TITLE_ARTIST, TITLE, ARTIST }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchFilter

        if (searchInTabs != other.searchInTabs) return false
        return searchInText == other.searchInText
    }

    override fun hashCode(): Int {
        var result = searchInTabs.hashCode()
        result = 31 * result + searchInText.hashCode()
        return result
    }
}