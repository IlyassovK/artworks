package com.karim.framemedia.domain

import kotlinx.serialization.Serializable

@Serializable
data class Artwork(
    val title: String,
    val description: String,
    val imageId: String?,
    val authorName: String
)