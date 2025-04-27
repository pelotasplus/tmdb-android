package pl.pelotasplus.tmdb.features.list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.pelotasplus.tmdb.R
import pl.pelotasplus.tmdb.domain.model.Movie
import pl.pelotasplus.tmdb.features.list.ListContract
import pl.pelotasplus.tmdb.ui.ErrorContent

@Composable
internal fun ListContent(
    state: ListContract.State,
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
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
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    Modifier
                        .align(Alignment.Center)
                )
            }
        } else if (state.error != null) {
            ErrorContent(
                modifier = Modifier.padding(it),
                error = state.error,
                onRetryClick = onRetryClick
            )
        } else {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
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
private fun ListContentErrorPreview() {
    ListContent(
        state = ListContract.State(
            loading = false,
            error = Exception("Error")
        )
    )
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

