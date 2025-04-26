package pl.pelotasplus.tmdb.features.filters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.pelotasplus.tmdb.features.filters.composables.FiltersContent

@Composable
fun FiltersScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: FiltersViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    FiltersContent(
        state = state,
        modifier = modifier,
        onGenreSelect = {
        },
    )
}
