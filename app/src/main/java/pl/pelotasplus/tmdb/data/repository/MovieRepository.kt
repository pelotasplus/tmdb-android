package pl.pelotasplus.tmdb.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import pl.pelotasplus.tmdb.data.di.RETRY_COUNT
import pl.pelotasplus.tmdb.data.di.RETRY_TIMEOUT
import pl.pelotasplus.tmdb.data.model.toMovie
import pl.pelotasplus.tmdb.data.source.TmdbService
import pl.pelotasplus.tmdb.domain.model.Movie
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val service: TmdbService,
) {
    fun getMovies(genreId: Int?): Flow<List<Movie>> {
        return flow {
            val response = service.getMovies(genreId)
            emit(response.results)
        }.map { movies ->
            movies.map { discoveryMovie ->
                val movieDetailsResponse = service.getMovieDetails(discoveryMovie.id)
                discoveryMovie.toMovie(movieDetailsResponse)
            }
        }.retryWhen { cause, attempt ->
            if (cause is HttpException && attempt < RETRY_COUNT) {
                delay(RETRY_TIMEOUT)
                true
            } else {
                false
            }
        }
    }
}
