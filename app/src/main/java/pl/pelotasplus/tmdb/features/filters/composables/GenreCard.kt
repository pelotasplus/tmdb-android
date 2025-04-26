package pl.pelotasplus.tmdb.features.filters.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.pelotasplus.tmdb.domain.model.Genre

@Composable
fun GenreCard(
    genre: Genre,
    modifier: Modifier = Modifier,
    onGenreSelect: (Genre) -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable(
                onClick = {
                    onGenreSelect(genre)
                }
            )
    ) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
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
