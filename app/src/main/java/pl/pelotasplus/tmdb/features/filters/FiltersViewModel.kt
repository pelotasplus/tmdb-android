package pl.pelotasplus.tmdb.features.filters

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import pl.pelotasplus.tmdb.features.filters.FiltersContract.Effect
import pl.pelotasplus.tmdb.features.filters.FiltersContract.State
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(State())
    internal val state = _state.asStateFlow()

    private val _effect = Channel<Effect>()
    internal val effect = _effect.receiveAsFlow()

    init {
    }
}
