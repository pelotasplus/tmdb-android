package pl.pelotasplus.tmdb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import pl.pelotasplus.tmdb.data.model.toMovie
import pl.pelotasplus.tmdb.data.source.TmdbService
import pl.pelotasplus.tmdb.domain.model.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: TmdbService,
) {
    fun getMovies(): Flow<List<Movie>> {
        return flow {
            val response = service.getMovies(null)
            emit(response.results)
        }.map {
            it.map { it.toMovie() }
        }
    }
}
