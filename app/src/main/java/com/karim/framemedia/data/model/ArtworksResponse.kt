package com.karim.framemedia.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworksResponse(
    @SerialName("pagination")
    val pagination: Pagination,
    @SerialName("data")
    val data: List<ArtworksData>
)