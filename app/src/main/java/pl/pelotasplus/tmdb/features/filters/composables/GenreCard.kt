package pl.pelotasplus.tmdb.features.filters.composables

import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.pelotasplus.tmdb.domain.model.Genre

@Composable
fun GenreCard(
    genre: Genre,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
private fun GenreCardPreview() {
    GenreCard(
        genre = Genre(
            name = "Action",
            id = 1,
            selected = false
        )
    )
}
