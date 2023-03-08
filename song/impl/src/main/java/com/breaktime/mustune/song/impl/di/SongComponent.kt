package com.breaktime.mustune.song.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.song.impl.presentation.SongViewModel
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.song.api.SongProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [CommonProvider::class, MusicManagerProvider::class],
    modules = [SongBinderModule::class, SongProviderModule::class]
)
interface SongComponent : SongProvider, CommonProvider, MusicManagerProvider {
    val viewModel: SongViewModel
}