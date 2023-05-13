package com.breaktime.mustune.song.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.domain.ErrorCode
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SongViewModel @Inject constructor(
    private val songId: String,
    private val musicManager: MusicManager
) : BaseViewModel<SongContract.Event, SongContract.State, SongContract.Effect>() {
    override fun createInitialState() = SongContract.State(isLoading = true)

    init {
        viewModelScope.launch {
            when (val outcome = musicManager.getSongFile(songId)) {
                is Outcome.Success -> setState {
                    copy(
                        songName = outcome.value.title,
                        file = outcome.value.file,
                        isLoading = false
                    )
                }

                is Outcome.Failure -> {
                    when (outcome) {
                        Outcome.Failure.Connection -> TODO()
                        is Outcome.Failure.Unknown -> TODO()
                        is Outcome.Failure.ServiceError -> {
                            when (outcome.code) {
                                ErrorCode.BAD_REQUEST -> TODO()
                                ErrorCode.UNAUTHORIZED -> TODO()
                                ErrorCode.NOT_FOUND -> TODO()
                                ErrorCode.INTERNAL_SERVER_ERROR -> TODO()
                                ErrorCode.Unknown -> TODO()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun handleEvent(event: SongContract.Event) {

    }
}