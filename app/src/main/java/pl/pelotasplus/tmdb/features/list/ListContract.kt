package pl.pelotasplus.tmdb.features.list

import androidx.compose.runtime.Stable
import pl.pelotasplus.tmdb.domain.model.Movie

interface ListContract {

    @Stable
    data class State(
        val loading: Boolean = true,
        val movies: List<Movie> = emptyList()
    )

    sealed interface Event {
        data object OnFabClicked : Event
    }

    sealed interface Effect {
        data object NavigateToFilters : Effect
    }
}
