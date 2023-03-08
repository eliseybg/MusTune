package com.breaktime.mustune.musicmanager.impl.data.source.songs.remote

import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongsApiService {
    @GET("allSongs")
    suspend fun getAllSongs(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("tab") tab: String
    ): Response<List<SongEntity>>

    @GET("allSongs")
    suspend fun searchSongs(
        @Query("searchText") searchText: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("tab") tab: String
    ): Response<List<SongEntity>>
}