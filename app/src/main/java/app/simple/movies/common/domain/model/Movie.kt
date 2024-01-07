package app.simple.movies.common.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val thumbPosterUrl: String?,
    val posterUrl: String?,
    val thumbBackdropUrl: String?,
    val backdropUrl: String?,
    val voteAverage: Float,
    val voteCount: Int
)