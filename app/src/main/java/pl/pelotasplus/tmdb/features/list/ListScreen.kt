package pl.pelotasplus.tmdb.features.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.pelotasplus.tmdb.features.list.composables.ListContent

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel<ListViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListContent(
        modifier = modifier,
        state = state
    )
}
