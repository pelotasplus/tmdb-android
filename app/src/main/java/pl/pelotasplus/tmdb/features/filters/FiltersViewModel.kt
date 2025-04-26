package pl.pelotasplus.tmdb.features.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import pl.pelotasplus.tmdb.data.repository.GenreRepository
import pl.pelotasplus.tmdb.features.filters.FiltersContract.Effect
import pl.pelotasplus.tmdb.features.filters.FiltersContract.State
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    geneRepository: GenreRepository
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    internal val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    internal val effect = _effect.receiveAsFlow()

    init {
        geneRepository.getGenres()
            .onEach { genres ->
                _state.update {
                    it.copy(
                        loading = false,
                        genres = genres
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}
