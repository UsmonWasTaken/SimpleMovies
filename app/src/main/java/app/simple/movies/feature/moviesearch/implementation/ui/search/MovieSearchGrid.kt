package app.simple.movies.feature.moviesearch.implementation.ui.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.ui.Dimens.bottomPadding

@Composable
fun MovieSearchGrid(movies: LazyPagingItems<Movie>, onMovieSelected: (Movie) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = bottomPadding)
    ) {
        items(movies.itemCount) { index ->
            movies[index]?.let { movie ->
                MovieItem(movie, onMovieSelected)
            }
        }
    }
}