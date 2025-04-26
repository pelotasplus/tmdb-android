package pl.pelotasplus.tmdb.features.list

import androidx.compose.runtime.Stable
import pl.pelotasplus.tmdb.domain.model.Movie

internal interface ListContract {

    @Stable
    data class State(
        val loading: Boolean = true,
        val movies: List<Movie> = emptyList()
    )

    sealed interface Event {

    }

    sealed interface Effect {

    }
}
