package com.breaktime.mustune.create_edit_file.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.create_edit_file.api.CreateEditFileProvider
import com.breaktime.mustune.create_edit_file.impl.presentation.CreateEditFileViewModel
import dagger.BindsInstance
import dagger.Component

@FeatureScoped
@Component(modules = [CreateEditFileBinderModule::class, CreateEditFileProviderModule::class])
interface CreateEditFileComponent : CreateEditFileProvider {
    val viewModel: CreateEditFileViewModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun songId(songId: String?): Builder
        fun build(): CreateEditFileComponent
    }
}