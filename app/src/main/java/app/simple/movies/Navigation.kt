package app.simple.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.simple.movies.di.LocalAppProvider
import app.simple.movies.entry.find
import app.simple.movies.feature.moviedetails.entry.MovieDetailsEntry
import app.simple.movies.feature.moviesearch.entry.MovieSearchEntry
import app.simple.movies.ui.BottomMenuBar

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val movieSearchScreen = destinations.find<MovieSearchEntry>()
    val movieDetailsScreen = destinations.find<MovieDetailsEntry>()

    Box(Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = movieSearchScreen.destination()) {
            movieSearchScreen.composable(navController, destinations)
            movieDetailsScreen.navigation(navController, destinations)
        }
    }

    Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
        BottomMenuBar(navController, destinations)
    }
}