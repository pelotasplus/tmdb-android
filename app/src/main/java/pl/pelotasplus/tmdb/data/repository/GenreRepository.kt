package pl.pelotasplus.tmdb.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import pl.pelotasplus.tmdb.data.di.RETRY_COUNT
import pl.pelotasplus.tmdb.data.di.RETRY_TIMEOUT
import pl.pelotasplus.tmdb.data.model.toGenre
import pl.pelotasplus.tmdb.data.source.TmdbService
import pl.pelotasplus.tmdb.domain.model.Genre
import retrofit2.HttpException
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val service: TmdbService,
) {
    fun getGenres(): Flow<List<Genre>> {
        return flow {
            val response = service.getGenres()
            emit(response.genres)
        }.map { genres ->
            genres.map { it.toGenre() }
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
