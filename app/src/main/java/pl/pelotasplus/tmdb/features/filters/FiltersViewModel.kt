package pl.pelotasplus.tmdb.features.filters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
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
import pl.pelotasplus.tmdb.MainDestinations
import pl.pelotasplus.tmdb.data.repository.GenreRepository
import pl.pelotasplus.tmdb.domain.model.Genre.Companion.AllGenres
import pl.pelotasplus.tmdb.features.filters.FiltersContract.Effect
import pl.pelotasplus.tmdb.features.filters.FiltersContract.Effect.NavigateBack
import pl.pelotasplus.tmdb.features.filters.FiltersContract.Event
import pl.pelotasplus.tmdb.features.filters.FiltersContract.State
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val geneRepository: GenreRepository
) : ViewModel() {

    private val navArgs by lazy {
        savedStateHandle.toRoute<MainDestinations.FilterList>()
    }

    private val _state = MutableStateFlow(State())
    internal val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    internal val effect = _effect.receiveAsFlow()

    init {
        onEvent(Event.LoadGenres(navArgs.selectedGenre))
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.OnBackClicked -> {
                viewModelScope.launch {
                    _effect.send(NavigateBack())
                }
            }

            is Event.OnGenreClicked -> {
                viewModelScope.launch {
                    _effect.send(NavigateBack(event.genre))
                }
            }

            is Event.LoadGenres -> {
                geneRepository.getGenres()
                    .onStart {
                        _state.update {
                            it.copy(
                                loading = true,
                                error = null
                            )
                        }
                    }
                    .onEach { genres ->
                        _state.update {
                            it.copy(
                                loading = false,
                                genres = listOf(AllGenres)
                                    .plus(genres)
                                    .map { it.copy(selected = it.id == event.genreId) }
                            )
                        }
                    }
                    .catch { error ->
                        _state.update {
                            it.copy(
                                loading = false,
                                error = error
                            )
                        }
                    }
                    .launchIn(viewModelScope)
            }

            Event.OnRetryClicked -> {
                onEvent(Event.LoadGenres(navArgs.selectedGenre))
            }
        }
    }
}
