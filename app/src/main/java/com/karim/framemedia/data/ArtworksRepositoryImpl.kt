package com.karim.framemedia.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.karim.framemedia.data.model.ArtworksData
import com.karim.framemedia.domain.ArtworksRepository
import kotlinx.coroutines.flow.Flow

class ArtworksRepositoryImpl(
    private val apiServices: ApiServices
) : ArtworksRepository {
    override fun getArtworks(): Flow<PagingData<ArtworksData>> = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 200),
        pagingSourceFactory = { ArtworksPagingSource(apiServices) }
    ).flow
}