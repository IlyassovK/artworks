package com.karim.framemedia.domain

import androidx.paging.PagingData
import com.karim.framemedia.data.model.ArtworksData
import kotlinx.coroutines.flow.Flow

interface ArtworksRepository {
    fun getArtworks(): Flow<PagingData<ArtworksData>>
}