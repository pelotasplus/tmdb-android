package pl.pelotasplus.tmdb.features.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.pelotasplus.tmdb.core.ObserveEffects
import pl.pelotasplus.tmdb.features.list.composables.ListContent

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel<ListViewModel>(),
    goToFilters: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEffects(viewModel.effect) { effect ->
        when (effect) {
            ListContract.Effect.NavigateToFilters -> goToFilters()
        }
    }

    ListContent(
        modifier = modifier,
        state = state,
        onFabClick = {
            viewModel.onEvent(ListContract.Event.OnFabClicked)
        }
    )
}
