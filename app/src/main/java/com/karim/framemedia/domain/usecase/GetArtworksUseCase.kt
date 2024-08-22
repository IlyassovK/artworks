package com.karim.framemedia.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.karim.framemedia.data.model.toArtwork
import com.karim.framemedia.domain.Artwork
import com.karim.framemedia.domain.ArtworksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class GetArtworksUseCase(private val repository: ArtworksRepository) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getArtworks(): Flow<PagingData<Artwork>> {
        return repository.getArtworks().mapLatest { pagingData ->
            pagingData.map { artworksData ->
                artworksData.toArtwork()
            }.filter { it.imageId != null }
        }
    }
}