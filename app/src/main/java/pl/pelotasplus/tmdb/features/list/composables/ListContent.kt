package pl.pelotasplus.tmdb.features.list.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pl.pelotasplus.tmdb.R
import pl.pelotasplus.tmdb.domain.model.Movie
import pl.pelotasplus.tmdb.features.list.ListContract

@Composable
internal fun ListContent(
    state: ListContract.State,
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onFabClick
            ) {
                Text(stringResource(R.string.list_filter_genres))
            }
        }
    ) {
        if (state.loading) {
            Box(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            LazyVerticalGrid(
                modifier = modifier
                    .padding(it),
                columns = GridCells.Fixed(3)
            ) {
                items(state.movies) { movie ->
                    MovieCard(movie)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListContentLoadingPreview() {
    ListContent(
        state = ListContract.State(
            loading = true
        )
    )
}

@Preview
@Composable
private fun ListContentLoadedPreview() {
    ListContent(
        state = ListContract.State(
            loading = false,
            movies = listOf(
                Movie(
                    title = "Movie 1",
                    cover = "https://image.tmdb.org/t/p/w500/movie1.jpg",
                    id = 1,
                    budget = 124f,
                    revenue = 567f,
                    rating = 4.123
                )
            )
        )
    )
}

