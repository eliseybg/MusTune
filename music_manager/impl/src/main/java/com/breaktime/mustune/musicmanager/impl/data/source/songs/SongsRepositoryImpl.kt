package com.breaktime.mustune.musicmanager.impl.data.source.songs

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.breaktime.mustune.common.Constants
import com.breaktime.mustune.musicmanager.impl.data.entities.SongEntity
import com.breaktime.mustune.musicmanager.api.models.MusicTab
import com.breaktime.mustune.musicmanager.impl.data.source.songs.local.SongsDatabase
import com.breaktime.mustune.musicmanager.impl.data.source.songs.remote.SongsApiService
import com.breaktime.mustune.musicmanager.impl.data.source.songs.remote.SongsRemoteMediator
import com.breaktime.mustune.musicmanager.impl.domain.repository.SongsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SongsRepositoryImpl @Inject constructor(
    private val songsDatabase: SongsDatabase,
    private val songsApiService: SongsApiService
) : SongsRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getSongs(tab: MusicTab): Flow<PagingData<SongEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.Pager.PAGE_SIZE,
                prefetchDistance = Constants.Pager.PREFETCH_DISTANCE,
                initialLoadSize = Constants.Pager.INITIAL_PAGE_SIZE
            ),
            pagingSourceFactory = {
                songsDatabase.songDao.getSongsInfo(tab.name)
            },
            remoteMediator = SongsRemoteMediator(
                tab,
                songsApiService,
                songsDatabase
            )
        ).flow
    }
}