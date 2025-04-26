package pl.pelotasplus.tmdb.domain.model

data class Genre(
    val id: Int,
    val name: String,
    val selected: Boolean
) {
    companion object {
        val AllGenres = Genre(-1, "All", true)
    }
}
