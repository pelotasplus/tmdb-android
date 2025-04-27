package pl.pelotasplus.tmdb.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val cover: String,
    val rating: Double,
    val budget: Float,
    val revenue: Float
) {
    fun cover(): String {
        return "https://image.tmdb.org/t/p/w500$cover"
    }
}

