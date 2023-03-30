package com.breaktime.mustune.create_edit_file.impl.di

import com.breaktime.mustune.common.di.FeatureScoped
import com.breaktime.mustune.create_edit_file.api.CreateEditFileProvider
import com.breaktime.mustune.create_edit_file.impl.presentation.CreateEditFileViewModel
import com.breaktime.mustune.file_manager.api.FileManagerProvider
import com.breaktime.mustune.musicmanager.api.MusicManagerProvider
import dagger.BindsInstance
import dagger.Component

@FeatureScoped
@Component(
    modules = [CreateEditFileBinderModule::class, CreateEditFileProviderModule::class],
    dependencies = [FileManagerProvider::class, MusicManagerProvider::class]
)
interface CreateEditFileComponent : CreateEditFileProvider {
    val viewModel: CreateEditFileViewModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun songId(songId: String?): Builder
        fun fileManagerProvider(fileManagerProvider: FileManagerProvider): Builder
        fun musicManagerProvider(musicManagerProvider: MusicManagerProvider): Builder
        fun build(): CreateEditFileComponent
    }
}