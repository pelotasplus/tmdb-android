package pl.pelotasplus.tmdb.features.filters

import androidx.compose.runtime.Stable
import pl.pelotasplus.tmdb.domain.model.Genre

interface FiltersContract {

    @Stable
    data class State(
        val loading: Boolean = true,
        val genres: List<Genre> = emptyList()
    )

    sealed interface Event {

    }

    sealed interface Effect {

    }
}
