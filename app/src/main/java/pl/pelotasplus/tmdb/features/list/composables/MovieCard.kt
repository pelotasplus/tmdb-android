package pl.pelotasplus.tmdb.features.list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pl.pelotasplus.tmdb.R
import pl.pelotasplus.tmdb.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.wrapContentSize()) {
        Box {
            AsyncImage(
                model = movie.cover(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.movie_card_rating, movie.rating),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(R.string.movie_card_revenue, movie.revenue),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = stringResource(R.string.movie_card_budget, movie.budget),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    MovieCard(
        movie = Movie(
            title = "Movie 1",
            cover = "https://image.tmdb.org/t/p/w500/movie1.jpg",
            id = 1,
            budget = 124f,
            revenue = 567f,
            rating = 6.987
        )
    )
}
