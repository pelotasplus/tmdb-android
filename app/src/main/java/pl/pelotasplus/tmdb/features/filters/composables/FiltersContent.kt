package pl.pelotasplus.tmdb.features.filters.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.pelotasplus.tmdb.R
import pl.pelotasplus.tmdb.domain.model.Genre
import pl.pelotasplus.tmdb.features.filters.FiltersContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersContent(
    state: FiltersContract.State,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onGenreSelect: (Genre) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.filters_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.global_go_back),
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.genres) {
                GenreCard(
                    genre = it,
                    onGenreSelect = onGenreSelect
                )
            }
        }
    }
}
