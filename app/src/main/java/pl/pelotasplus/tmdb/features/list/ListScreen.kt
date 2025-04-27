package pl.pelotasplus.tmdb.features.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.pelotasplus.tmdb.core.ObserveEffects
import pl.pelotasplus.tmdb.features.list.composables.ListContent

@Composable
fun ListScreen(
    viewModel: ListViewModel,
    modifier: Modifier = Modifier,
    goToFilters: (Int?) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEffects(viewModel.effect) { effect ->
        when (effect) {
            is ListContract.Effect.NavigateToFilters -> goToFilters(effect.genreId)
        }
    }

    ListContent(
        modifier = modifier,
        state = state,
        onFabClick = {
            viewModel.onEvent(ListContract.Event.OnFabClicked)
        },
        onRetryClick = {
            viewModel.onEvent(ListContract.Event.OnRetryClicked)
        },
    )
}
