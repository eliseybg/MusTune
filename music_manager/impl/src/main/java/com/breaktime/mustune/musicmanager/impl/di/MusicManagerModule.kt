package com.breaktime.mustune.musicmanager.impl.di

import com.breaktime.mustune.musicmanager.api.MusicManager
import com.breaktime.mustune.musicmanager.impl.MusicManagerImpl
import com.breaktime.mustune.musicmanager.impl.data.source.songs.SongsRepositoryImpl
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, StorageModule::class])
interface MusicManagerModule {
    @Binds
    fun bindSongRepository(songsRepository: SongsRepositoryImpl): SongsRepository

    @Binds
    fun bindMusicManager(musicManager: MusicManagerImpl): MusicManager
}