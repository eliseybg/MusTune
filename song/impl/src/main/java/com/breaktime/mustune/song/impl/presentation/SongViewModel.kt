package com.breaktime.mustune.song.impl.presentation

import androidx.lifecycle.viewModelScope
import com.breaktime.mustune.common.UiText
import com.breaktime.mustune.common.domain.ErrorCode
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.common.presentation.BaseViewModel
import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.resources.R
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

                is Outcome.Failure -> when (outcome) {
                    Outcome.Failure.Connection -> setEffect {
                        SongContract.Effect.ErrorMessage(
                            UiText.StringResource(R.string.no_internet)
                        )
                    }

                    is Outcome.Failure.Unknown -> setEffect {
                        SongContract.Effect.ErrorMessage(
                            UiText.StringResource(R.string.unknown_exception)
                        )
                    }

                    is Outcome.Failure.ServiceError -> when (outcome.code) {
                        ErrorCode.BAD_REQUEST -> setEffect {
                            SongContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.bad_request)
                            )
                        }

                        ErrorCode.UNAUTHORIZED -> setEffect {
                            SongContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.unauthorized)
                            )
                        }

                        ErrorCode.NOT_FOUND -> setEffect {
                            SongContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.no_song)
                            )
                        }

                        ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.Unknown -> setEffect {
                            SongContract.Effect.ErrorMessage(
                                UiText.StringResource(R.string.unknown_exception)
                            )
                        }
                    }
                }
            }
        }
    }

    override fun handleEvent(event: SongContract.Event) {

    }
}