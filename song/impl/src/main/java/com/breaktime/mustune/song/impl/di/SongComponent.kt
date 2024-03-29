package com.breaktime.mustune.song.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.song.api.SongProvider
import com.breaktime.mustune.song.impl.presentation.SongViewModel
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [MusicManagerProvider::class],
    modules = [SongBinderModule::class, SongProviderModule::class]
)
interface SongComponent : SongProvider, MusicManagerProvider {
    val viewModel: SongViewModel
}