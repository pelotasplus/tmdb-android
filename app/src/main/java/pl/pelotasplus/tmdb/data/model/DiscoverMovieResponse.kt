package pl.pelotasplus.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.pelotasplus.tmdb.domain.model.Movie

@Serializable
data class DiscoverMovieResponse(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<DiscoverMovie>,
    @SerialName("total_pages") val totalPages: Int,
) {
    @Serializable
    data class DiscoverMovie(
        @SerialName("id") val id: Long,
        @SerialName("poster_path") val posterPath: String,
        @SerialName("title") val title: String,
        @SerialName("vote_average") val voteAverage: Double,
    )
}

fun DiscoverMovieResponse.DiscoverMovie.toMovie(movieDetails: MovieDetailsResponse) =
    Movie(
        id = id,
        title = title,
        cover = posterPath,
        budget = movieDetails.budget / 1_000_000f,
        revenue = movieDetails.revenue / 1_000_000f,
        rating = voteAverage,
    )
