package com.breaktime.mustune.music.impl.presentation

import com.breaktime.mustune.common.presentation.BaseViewModel
import javax.inject.Inject

class MusicViewModel @Inject constructor(
) : BaseViewModel<SettingsContract.Event, SettingsContract.State, SettingsContract.Effect>() {
    override fun createInitialState() = SettingsContract.State()

    override fun handleEvent(event: SettingsContract.Event) {

    }
}