package pl.pelotasplus.tmdb.features.filters.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.pelotasplus.tmdb.features.filters.FiltersContract

@Composable
fun FiltersContent(
    state: FiltersContract.State,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(state.genres) {
            GenreCard(
                genre = it,
            )

        }
    }

}
