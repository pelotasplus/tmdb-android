package pl.pelotasplus.tmdb.data.source

import pl.pelotasplus.tmdb.data.model.DiscoverMovieResponse
import pl.pelotasplus.tmdb.data.model.GenresResponse
import pl.pelotasplus.tmdb.data.model.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("with_genres") genres: Int?
    ): DiscoverMovieResponse

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Long
    ): MovieDetailsResponse

    @GET("/3/genre/movie/list")
    suspend fun getGenres(): GenresResponse
}
