package com.breaktime.mustune.song_bottom_sheet

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [CommonProvider::class, MusicManagerProvider::class]
)
interface SongBottomSheetComponent : MusicManagerProvider {
    val viewModel: SongBottomSheetViewModel
}