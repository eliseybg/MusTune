package com.breaktime.mustune.musicmanager.impl.di

import android.content.Context
import androidx.room.Room
import com.breaktime.mustune.musicmanager.impl.data.source.songs.local.SongsDao
import com.breaktime.mustune.musicmanager.impl.data.source.songs.local.SongsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): SongsDatabase =
        Room.databaseBuilder(context, SongsDatabase::class.java, "songs_database").build()
}