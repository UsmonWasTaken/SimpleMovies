package app.simple.movies.feature.moviesearch.implementation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import app.simple.movies.common.di.LocalCommonProvider
import app.simple.movies.entry.Destinations
import app.simple.movies.common.injectedViewModel
import app.simple.movies.entry.find
import app.simple.movies.data.LocalDataProvider
import app.simple.movies.feature.moviedetails.entry.MovieDetailsEntry
import app.simple.movies.feature.moviesearch.entry.MovieSearchEntry
import app.simple.movies.feature.moviesearch.implementation.di.DaggerMovieSearchComponent
import app.simple.movies.feature.moviesearch.implementation.ui.MovieSearchScreen
import javax.inject.Inject

class MovieSearchEntryImpl @Inject constructor() : MovieSearchEntry() {

    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        val dataProvider = LocalDataProvider.current
        val commonProvider = LocalCommonProvider.current
        val viewModel = injectedViewModel {
            DaggerMovieSearchComponent.builder()
                .dataProvider(dataProvider)
                .commonProvider(commonProvider)
                .build()
                .viewModel
        }

        MovieSearchScreen(viewModel, onExpandMovieDetails = { movie ->
            val destination = destinations
                .find<MovieDetailsEntry>()
                .destination(movie.id)
            navController.navigate(destination)
        })
    }
}