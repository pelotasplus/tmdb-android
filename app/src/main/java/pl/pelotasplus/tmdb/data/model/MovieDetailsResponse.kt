package pl.pelotasplus.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("budget") val budget: Long,
    @SerialName("revenue") val revenue: Long,
)
