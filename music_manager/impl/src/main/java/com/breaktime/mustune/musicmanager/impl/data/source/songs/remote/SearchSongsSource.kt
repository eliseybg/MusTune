package com.breaktime.mustune.musicmanager.impl.data.source.songs.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import retrofit2.HttpException
import java.io.IOException

class SearchSongsSource(
    private val searchText: String,
    private val songsApiService: SongsApiService,
) : PagingSource<Int, SongEntity>() {
    override fun getRefreshKey(state: PagingState<Int, SongEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SongEntity> {
        val page = params.key ?: Constants.Pager.INITIAL_PAGE
        return try {
            val response = songsApiService.searchSongs(searchText, page, params.loadSize)
            if (!response.isSuccessful) return LoadResult.Error(Exception(response.message()))
            val participants = response.body() ?: return LoadResult.Error(Exception("No data"))
            val nextKey = if (participants.isEmpty()) null else page + 1
            LoadResult.Page(
                data = participants,
                prevKey = if (page == Constants.Pager.INITIAL_PAGE) null else page - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}