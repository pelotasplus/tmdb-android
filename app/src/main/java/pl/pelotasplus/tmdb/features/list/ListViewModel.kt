package pl.pelotasplus.tmdb.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import pl.pelotasplus.tmdb.data.repository.MovieRepository
import pl.pelotasplus.tmdb.features.list.ListContract.Effect
import pl.pelotasplus.tmdb.features.list.ListContract.State
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    internal val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    internal val effect = _effect.receiveAsFlow()

    init {
        movieRepository.getMovies()
            .onEach { movies ->
                _state.update {
                    it.copy(
                        loading = false,
                        movies = movies
                    )
                }
            }
            .catch {
                it.printStackTrace()
            }
            .launchIn(viewModelScope)
    }
}
