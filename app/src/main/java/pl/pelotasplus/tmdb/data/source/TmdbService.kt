package pl.pelotasplus.tmdb.data.source

import pl.pelotasplus.tmdb.data.model.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("with_genres") genres: String?
    ): DiscoverMovieResponse
}
