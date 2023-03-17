package com.breaktime.mustune.search_songs.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.search_songs.api.SearchSongsProvider
import com.breaktime.mustune.search_songs.impl.presentation.SearchSongsViewModel
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [MusicManagerProvider::class],
    modules = [SearchSongsBinderModule::class, SearchSongsProviderModule::class]
)
interface SearchSongsComponent : SearchSongsProvider, MusicManagerProvider {
    val viewModel: SearchSongsViewModel
}