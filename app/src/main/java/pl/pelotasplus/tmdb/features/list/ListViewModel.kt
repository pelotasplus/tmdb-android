package pl.pelotasplus.tmdb.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.pelotasplus.tmdb.data.repository.MovieRepository
import pl.pelotasplus.tmdb.features.list.ListContract.Effect
import pl.pelotasplus.tmdb.features.list.ListContract.Effect.NavigateToFilters
import pl.pelotasplus.tmdb.features.list.ListContract.State
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: ListContract.Event) {
        when (event) {
            ListContract.Event.OnFabClicked -> {
                viewModelScope.launch {
                    _effect.send(NavigateToFilters(_state.value.genreId))
                }
            }

            is ListContract.Event.LoadMovies -> {
                if (_state.value.genreId == event.genreId && !event.forceRefresh)
                    return

                movieRepository.getMovies(event.genreId)
                    .onStart {
                        _state.update {
                            it.copy(
                                loading = true,
                                error = null
                            )
                        }
                    }
                    .onEach { movies ->
                        _state.update {
                            it.copy(
                                loading = false,
                                genreId = event.genreId,
                                movies = movies,
                                error = null
                            )
                        }
                    }
                    .catch { error ->
                        println("XXX error $error")
                        _state.update {
                            it.copy(
                                loading = false,
                                error = error,
                                genreId = event.genreId,
                            )
                        }
                    }
                    .launchIn(viewModelScope)
            }

            ListContract.Event.OnRetryClicked -> {
                onEvent(
                    ListContract.Event.LoadMovies(
                        genreId = _state.value.genreId,
                        forceRefresh = true
                    )
                )
            }
        }
    }
}
