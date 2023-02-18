package com.breaktime.mustune.music.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.music.api.MusicProvider
import com.breaktime.mustune.music.impl.presentation.MusicViewModel
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [CommonProvider::class],
    modules = [MusicBinderModule::class, MusicProviderModule::class]
)
interface MusicComponent : MusicProvider, CommonProvider {
    val viewModel: MusicViewModel
}