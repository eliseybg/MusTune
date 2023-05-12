package com.breaktime.mustune.song.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SongViewModel @Inject constructor(
    private val songId: String,
    private val musicManager: MusicManager
) : BaseViewModel<SongContract.Event, SongContract.State, SongContract.Effect>() {
    override fun createInitialState() = SongContract.State()

    init {
        viewModelScope.launch {
            when (val song = musicManager.getSong(songId, true)) {
                is Outcome.Success -> {
                    setState { copy(songName = song.value.title) }
                }

                is Outcome.Failure<*> -> {}
            }
        }
    }

    override fun handleEvent(event: SongContract.Event) {

    }
}