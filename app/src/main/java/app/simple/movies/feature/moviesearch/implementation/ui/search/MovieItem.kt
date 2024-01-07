package app.simple.movies.feature.moviesearch.implementation.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.ui.ProgressiveGlowingImage

@Composable
fun MovieItem(
    movie: Movie,
    onMovieSelected: (movie: Movie) -> Unit
) {
    val posterUrl = movie.posterUrl
    val thumbPosterUrl = movie.thumbPosterUrl
    if (posterUrl != null && thumbPosterUrl != null) {
        ProgressiveGlowingImage(
            url = posterUrl,
            thumbUrl = thumbPosterUrl,
            glow = true,
            Modifier.clickable { onMovieSelected(movie) })
    }
}