package pl.pelotasplus.tmdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pl.pelotasplus.tmdb.domain.model.Genre

@Serializable
data class GenresResponse(
    @SerialName("genres") val genres: List<Genre>
) {

    @Serializable
    data class Genre(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String
    )
}

fun GenresResponse.Genre.toGenre() =
    Genre(id, name, false)
