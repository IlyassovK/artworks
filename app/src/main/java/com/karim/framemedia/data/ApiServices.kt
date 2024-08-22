package com.karim.framemedia.data

import com.karim.framemedia.data.model.ArtworksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/api/v1/artworks")
    suspend fun getArtworks(@Query("page") page: Int): Response<ArtworksResponse>
}