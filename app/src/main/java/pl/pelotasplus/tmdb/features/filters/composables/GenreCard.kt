package pl.pelotasplus.tmdb.features.filters.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            ),
        colors = CardDefaults.cardColors().copy(
            containerColor = if (genre.selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.titleMedium,
            color = if (genre.selected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurface
            },
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

@Preview
@Composable
private fun GenreCardSelectedPreview() {
    GenreCard(
        genre = Genre(
            name = "Action",
            id = 1,
            selected = true
        )
    )
}
