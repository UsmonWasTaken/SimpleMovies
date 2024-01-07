package app.simple.movies.entry

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface AggregateFeatureEntry : FeatureEntry {

    context(NavGraphBuilder)
    fun navigation(navController: NavHostController, destinations: Destinations)
}
