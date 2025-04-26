package pl.pelotasplus.tmdb

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import pl.pelotasplus.tmdb.features.filters.FiltersScreen
import pl.pelotasplus.tmdb.features.list.ListScreen

sealed interface MainDestinations {
    @Serializable
    data object MovieList : MainDestinations

    @Serializable
    data class SelectGenere(
        val selectedGenere: Int,
    ) : MainDestinations
}

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
        composable<MainDestinations.MovieList> {
            ListScreen(
                goToFilters = {
                    navController.navigate(MainDestinations.SelectGenere(-1))
                }
            )
        }

        composable<MainDestinations.SelectGenere> {
            FiltersScreen()
        }
    }
}
