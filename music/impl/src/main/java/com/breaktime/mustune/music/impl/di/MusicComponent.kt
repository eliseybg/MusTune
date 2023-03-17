package com.breaktime.mustune.music.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.music.api.MusicProvider
import com.breaktime.mustune.music.impl.presentation.MusicViewModel
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [MusicManagerProvider::class],
    modules = [MusicBinderModule::class, MusicProviderModule::class]
)
interface MusicComponent : MusicProvider, MusicManagerProvider {
    val viewModel: MusicViewModel
}