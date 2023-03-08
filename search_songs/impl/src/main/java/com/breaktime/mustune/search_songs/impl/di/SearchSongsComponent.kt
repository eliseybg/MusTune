package com.breaktime.mustune.search_songs.impl.di

import com.breaktime.mustune.common.di.CommonProvider
import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.search_songs.impl.presentation.SearchSongsViewModel
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import com.breaktime.mustune.search_songs.api.SearchSongsProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = [CommonProvider::class, MusicManagerProvider::class],
    modules = [SearchSongsBinderModule::class, SearchSongsProviderModule::class]
)
interface SearchSongsComponent : SearchSongsProvider, CommonProvider, MusicManagerProvider {
    val viewModel: SearchSongsViewModel
}