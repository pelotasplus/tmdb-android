package pl.pelotasplus.tmdb.features.filters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.pelotasplus.tmdb.core.ObserveEffects
import pl.pelotasplus.tmdb.domain.model.Genre
import pl.pelotasplus.tmdb.features.filters.composables.FiltersContent

@Composable
fun FiltersScreen(
    viewModel: FiltersViewModel,
    modifier: Modifier = Modifier,
    goToList: (Genre?) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEffects(viewModel.effect) { effect ->
        when (effect) {
            is FiltersContract.Effect.NavigateBack -> {
                goToList(effect.selection)
            }
        }
    }

    FiltersContent(
        state = state,
        modifier = modifier,
        onGenreSelect = {
            viewModel.onEvent(FiltersContract.Event.OnGenreClicked(it))
        },
        onBackClick = {
            viewModel.onEvent(FiltersContract.Event.OnBackClicked)
        },
        onRetryClick = {
            viewModel.onEvent(FiltersContract.Event.OnRetryClicked)
        }
    )
}
