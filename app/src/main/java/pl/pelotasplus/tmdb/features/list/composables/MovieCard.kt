package pl.pelotasplus.tmdb.features.list.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.pelotasplus.tmdb.R
import pl.pelotasplus.tmdb.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.height(150.dp)) {
        Column {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium
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
