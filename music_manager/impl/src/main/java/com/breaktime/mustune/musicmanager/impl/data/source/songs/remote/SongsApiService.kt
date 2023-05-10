package com.breaktime.mustune.musicmanager.impl.data.source.songs.remote

import com.breaktime.mustune.musicmanager.impl.data.entities.SearchSongsBody
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.impl.data.entities.SongIdBody
import com.breaktime.mustune.musicmanager.impl.data.entities.SongInfoBody
import com.breaktime.mustune.musicmanager.impl.data.entities.TabQuery
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface SongsApiService {
    @GET("song")
    suspend fun getSong(@Query("songId") songId: String): Response<SongEntity>

    @GET("songsCategories")
    suspend fun getSongsCategories(): Response<List<TabQuery>>

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

    @Multipart
    @POST("addSong")
    suspend fun addSong(
        @Part("songInfo") songInfo: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<SongEntity>

    @POST("editSong")
    suspend fun editSong(@Body body: SongInfoBody): Response<SongEntity>

    @DELETE("deleteSong")
    suspend fun deleteSong(@Query("songId") songId: String): Response<Unit>

    @POST("addSongToFavourite")
    suspend fun addSongToFavourite(@Body body: SongIdBody): Response<SongEntity>

    @POST("removeSongFromFavourite")
    suspend fun removeSongFromFavourite(@Body body: SongIdBody): Response<SongEntity>
}