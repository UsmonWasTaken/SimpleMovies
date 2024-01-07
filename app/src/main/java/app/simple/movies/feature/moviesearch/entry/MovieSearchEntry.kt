package app.simple.movies.feature.moviesearch.entry

import app.simple.movies.entry.ComposableFeatureEntry

abstract class MovieSearchEntry : ComposableFeatureEntry() {

    final override val featureRoute = "movie-search"

    fun destination() = featureRoute
}