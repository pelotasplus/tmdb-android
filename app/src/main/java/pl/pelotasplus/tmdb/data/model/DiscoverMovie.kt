package pl.pelotasplus.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.pelotasplus.tmdb.domain.model.Movie

@Serializable
data class DiscoverMovie(
    @SerialName("id") val id: Long,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("title") val title: String,
    @SerialName("vote_average") val voteAverage: Double,
)

fun DiscoverMovie.toMovie() =
    Movie(
        title = title,
        cover = posterPath
    )
