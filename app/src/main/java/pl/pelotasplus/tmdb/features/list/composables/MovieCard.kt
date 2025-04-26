package pl.pelotasplus.tmdb.features.list.composables

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.pelotasplus.tmdb.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.height(150.dp)) {
        Text(
            text = movie.title
        )
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    MovieCard(
        movie = Movie(
            title = "Movie title",
            cover = "http://example.com/image.jpg"
        )
    )
}
