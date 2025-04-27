package pl.pelotasplus.tmdb.features.list

import androidx.compose.runtime.Stable
import pl.pelotasplus.tmdb.domain.model.Movie

interface ListContract {

    @Stable
    data class State(
        val genreId: Int? = -1,
        val loading: Boolean = true,
        val error: Throwable? = null,
        val movies: List<Movie> = emptyList()
    )

    sealed interface Event {
        data object OnRetryClicked : Event
        data object OnFabClicked : Event
        data class LoadMovies(
            val genreId: Int?,
            val forceRefresh: Boolean = false
        ) : Event
    }

    sealed interface Effect {
        data class NavigateToFilters(val genreId: Int?) : Effect
    }
}
