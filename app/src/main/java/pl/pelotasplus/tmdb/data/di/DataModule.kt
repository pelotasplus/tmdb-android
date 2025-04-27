package pl.pelotasplus.tmdb.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.pelotasplus.tmdb.data.source.AuthorizationInterceptor
import pl.pelotasplus.tmdb.data.source.TmdbService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val READ_ACCESS_TOKEN = "DataModule/READ_ACCESS_TOKEN"
private const val BASE_URL = "DataModule/BASE_URL"
const val RETRY_COUNT = 3
const val NETWORK_TIMEOUT = 30_000L
const val RETRY_TIMEOUT = 5_000L

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Named(READ_ACCESS_TOKEN)
    @Provides
    @Suppress("FunctionOnlyReturningConstant", "MaxLineLength")
    fun provideAuthorizationToken() =
        ""

    @Named(BASE_URL)
    @Provides
    @Suppress("FunctionOnlyReturningConstant")
    fun provideBaseUrl() =
        "https://api.themoviedb.org"

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        ).apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    fun provideClient(
        @Named(READ_ACCESS_TOKEN) token: String,
        loggingInterceptor: HttpLoggingInterceptor
    ) =
        OkHttpClient.Builder()
            .callTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(AuthorizationInterceptor(token))
            .addInterceptor(loggingInterceptor)
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
