package com.karim.framemedia.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karim.framemedia.data.model.ArtworksData

class ArtworksPagingSource(
    private val apiServices: ApiServices
) : PagingSource<Int, ArtworksData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtworksData> {
        return try {
            val position = params.key ?: 1
            val response = apiServices.getArtworks(position)
            val data = response.body()?.data
            if (response.isSuccessful && data != null) {
                LoadResult.Page(
                    data = data,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == response.body()?.pagination?.total_pages) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtworksData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}