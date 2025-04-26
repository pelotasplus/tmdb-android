package pl.pelotasplus.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverMovieResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<DiscoverMovie>,
    @SerialName("total_pages") val totalPages: Int,
)
