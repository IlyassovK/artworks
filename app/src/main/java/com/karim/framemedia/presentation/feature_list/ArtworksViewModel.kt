package com.karim.framemedia.presentation.feature_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karim.framemedia.data.model.ArtworksData
import com.karim.framemedia.domain.Artwork
import com.karim.framemedia.domain.ArtworksRepository
import com.karim.framemedia.domain.usecase.GetArtworksUseCase
import kotlinx.coroutines.flow.Flow

class ArtworksViewModel(
    private val getArtworksUseCase: GetArtworksUseCase
) : ViewModel() {

    val moviesList: Flow<PagingData<Artwork>> =
        getArtworksUseCase.getArtworks()
            .cachedIn(viewModelScope)
}