package com.breaktime.mustune.musicmanager.impl.data.source.songs.remote

import com.breaktime.mustune.musicmanager.impl.data.entities.SearchSongsBody
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SongsApiService {
    @GET("allSongs")
    suspend fun getAllSongs(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("tab") tab: String
    ): Response<List<SongEntity>>

    @POST("searchSongs")
    suspend fun searchSongs(
        @Body body: SearchSongsBody,
    ): Response<List<SongEntity>>
}