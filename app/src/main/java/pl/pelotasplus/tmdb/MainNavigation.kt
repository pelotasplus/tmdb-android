package pl.pelotasplus.tmdb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import pl.pelotasplus.tmdb.features.filters.FiltersScreen
import pl.pelotasplus.tmdb.features.filters.FiltersViewModel
import pl.pelotasplus.tmdb.features.list.ListContract
import pl.pelotasplus.tmdb.features.list.ListScreen
import pl.pelotasplus.tmdb.features.list.ListViewModel

sealed interface MainDestinations {
    @Serializable
    data object MovieList : MainDestinations

    @Serializable
    data class FilterList(
        val selectedGenre: Int?,
    ) : MainDestinations
}

private const val SELECTED_GENRE_KEY = "ListViewModel/SELECTED_GENRE_KEY"

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainDestinations.MovieList
    ) {
        composable<MainDestinations.MovieList> { navBackStackEntry ->
            val viewModel = hiltViewModel<ListViewModel>()

            LaunchedEffect(Unit) {
                navBackStackEntry.savedStateHandle
                    .getStateFlow<Int?>(SELECTED_GENRE_KEY, null)
                    .collect { genreId ->
                        viewModel.onEvent(ListContract.Event.LoadMovies(genreId))
                    }
            }

            ListScreen(
                viewModel = viewModel,
                goToFilters = {
                    navController.navigate(MainDestinations.FilterList(it))
                }
            )
        }

        composable<MainDestinations.FilterList> { navBackStackEntry ->
            val viewModel: FiltersViewModel = hiltViewModel()

            FiltersScreen(
                viewModel = viewModel,
                goToList = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle?.set(SELECTED_GENRE_KEY, it?.id)
                    navController.navigateUp()
                }
            )
        }
    }
}
