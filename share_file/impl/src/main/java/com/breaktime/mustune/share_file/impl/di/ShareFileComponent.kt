package com.breaktime.mustune.share_file.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.share_file.impl.presentation.ShareFileViewModel
import com.breaktime.mustune.share_file.api.ShareFileProvider
import dagger.Component

@FeatureScoped
@Component(
    dependencies = []
)
interface ShareFileComponent : ShareFileProvider {
    val viewModel: ShareFileViewModel
}