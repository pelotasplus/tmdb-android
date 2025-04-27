package pl.pelotasplus.tmdb

import kotlinx.serialization.Serializable

sealed interface MainDestinations {
    @Serializable
    data object MovieList : MainDestinations

    @Serializable
    data class FilterList(
        val selectedGenre: Int?,
    ) : MainDestinations
}
