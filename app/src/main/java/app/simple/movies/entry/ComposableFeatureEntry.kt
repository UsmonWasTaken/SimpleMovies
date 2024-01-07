package app.simple.movies.entry

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

abstract class ComposableFeatureEntry : FeatureEntry {

    context(NavGraphBuilder)
    fun composable(navController: NavHostController, destinations: Destinations) {
        composable(featureRoute, arguments, deepLinks) { backStackEntry ->
            Composable(navController, destinations, backStackEntry)
        }
    }

    @Composable
    protected abstract fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    )
}
