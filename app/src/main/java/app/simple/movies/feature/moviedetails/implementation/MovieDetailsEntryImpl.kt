package app.simple.movies.feature.moviedetails.implementation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.simple.movies.common.RootComponentHolder
import app.simple.movies.common.di.LocalCommonProvider
import app.simple.movies.common.injectedViewModel
import app.simple.movies.common.rememberBackStackEntry
import app.simple.movies.common.rememberScoped
import app.simple.movies.data.LocalDataProvider
import app.simple.movies.entry.Destinations
import app.simple.movies.feature.moviedetails.entry.MovieDetailsEntry
import app.simple.movies.feature.moviedetails.implementation.credits.di.DaggerCreditsComponent
import app.simple.movies.feature.moviedetails.implementation.credits.ui.CreditsScreen
import app.simple.movies.feature.moviedetails.implementation.di.DaggerMovieDetailsComponent
import app.simple.movies.feature.moviedetails.implementation.di.MovieDetailsComponent
import app.simple.movies.feature.moviedetails.implementation.movie.di.DaggerMovieComponent
import app.simple.movies.feature.moviedetails.implementation.movie.ui.MovieScreen
import javax.inject.Inject

class MovieDetailsEntryImpl @Inject constructor() : MovieDetailsEntry(),
    RootComponentHolder<MovieDetailsComponent> {

    override val rootRoute = "@movie-details"

    private val creditsRoute = "credits/{$ARG_MOVIE_ID}"
    private fun creditsDestination(movieId: Int) = "credits/$movieId"

    context(NavGraphBuilder)
    override fun navigation(
        navController: NavHostController,
        destinations: Destinations
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {

            composable(route = featureRoute, arguments) {
                val movieId = it.arguments?.getInt(ARG_MOVIE_ID)!!

                val rootEntry = it.rememberBackStackEntry(navController, route = rootRoute)
                val rootComponent = rootComponent(rootEntry, it.arguments)

                val viewModel = injectedViewModel(rootEntry) {
                    DaggerMovieComponent.builder()
                        .movieDetailsComponent(rootComponent)
                        .build()
                        .viewModel
                }

                MovieScreen(
                    viewModel,
                    onShowCredits = { navController.navigate(creditsDestination(movieId)) },
                )
            }

            composable(route = creditsRoute, arguments) {
                val rootComponent = rootComponent(currentEntry = it, navController, it.arguments)

                val viewModel = injectedViewModel {
                    DaggerCreditsComponent.builder()
                        .movieDetailsComponent(rootComponent)
                        .build()
                        .viewModel
                }

                CreditsScreen(viewModel)
            }
        }
    }

    @Composable
    override fun rootComponent(
        rootEntry: NavBackStackEntry,
        arguments: Bundle?
    ): MovieDetailsComponent {
        val movieId = arguments?.getInt(ARG_MOVIE_ID)!!
        val dataProvider = LocalDataProvider.current
        val commonProvider = LocalCommonProvider.current

        return rememberScoped(rootEntry) {
            DaggerMovieDetailsComponent.factory().create(dataProvider, commonProvider, movieId)
        }
    }
}