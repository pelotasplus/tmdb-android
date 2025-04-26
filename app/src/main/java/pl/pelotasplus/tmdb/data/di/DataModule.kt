package pl.pelotasplus.tmdb.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import pl.pelotasplus.tmdb.data.source.AuthorizationInterceptor
import pl.pelotasplus.tmdb.data.source.TmdbService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Named

private const val READ_ACCESS_TOKEN = "DataModule/READ_ACCESS_TOKEN"
private const val BASE_URL = "DataModule/BASE_URL"

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Named(READ_ACCESS_TOKEN)
    @Provides
    fun provideAuthorizationToken() =
        ""

    @Named(BASE_URL)
    @Provides
    fun provideBaseUrl() =
        "https://api.themoviedb.org"


    @Provides
    fun provideClient(@Named(READ_ACCESS_TOKEN) token: String) =
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token))
            .build()

    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideTmdbService(
        json: Json,
        client: OkHttpClient,
        @Named(BASE_URL) baseUrl: String,
    ) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(TmdbService::class.java)
}
