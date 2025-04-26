package pl.pelotasplus.tmdb.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import pl.pelotasplus.tmdb.data.model.toGenre
import pl.pelotasplus.tmdb.data.source.TmdbService
import pl.pelotasplus.tmdb.domain.model.Genre
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
        }
    }
}
