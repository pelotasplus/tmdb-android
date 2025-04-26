package pl.pelotasplus.tmdb.features.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import pl.pelotasplus.tmdb.domain.model.Movie
import pl.pelotasplus.tmdb.features.list.ListContract.Effect
import pl.pelotasplus.tmdb.features.list.ListContract.State
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(State())
    internal val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    internal val effect = _effect.receiveAsFlow()

    init {
        _state.update {
            it.copy(
                movies = listOf(
                    Movie("Movie 1", "https://example.com/image.jpg"),
                    Movie("Movie 2", "https://example.com/image.jpg"),
                    Movie("Movie 3", "https://example.com/image.jpg"),
                )
            )
        }
    }
}
