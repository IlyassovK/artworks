package com.karim.framemedia.data.model

import com.google.gson.annotations.SerializedName
import com.karim.framemedia.domain.Artwork
import kotlinx.serialization.Serializable

@Serializable
data class ArtworksData(
    @SerializedName("artist_display")
    val artistDisplay: String?,
    @SerializedName("artist_id")
    val artistId: Int?,
    @SerializedName("artist_title")
    val artistTitle: String?,
    @SerializedName("artwork_type_title")
    val artworkTypeTitle: String?,
    @SerializedName("category_titles")
    val categoryTitles: List<String>?,
    @SerializedName("date_display")
    val dateDisplay: String?,
    @SerializedName("date_end")
    val dateEnd: Int?,
    @SerializedName("date_qualifier_title")
    val dateQualifierTitle: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("date_start")
    val dateStart: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_id")
    val imageId: String?,
    @SerializedName("material_titles")
    val materialTitles: List<String>?,
    @SerializedName("place_of_origin")
    val placeOfOrigin: String?,
    @SerializedName("provenance_text")
    val provenanceText: String?,
    @SerializedName("publication_history")
    val publicationHistory: String?,
    @SerializedName("timestamp")
    val timestamp: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
)

fun ArtworksData.toArtwork() = Artwork(
    title = this.title ?: artistDisplay ?: "",
    description = this.description ?: this.publicationHistory ?: "",
    imageId = this.imageId,
    authorName = this.artistDisplay ?: ""
)