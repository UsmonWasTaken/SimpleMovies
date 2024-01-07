package app.simple.movies.di

import dagger.Module
import app.simple.movies.feature.moviedetails.implementation.di.MovieDetailsEntryModule
import app.simple.movies.feature.moviesearch.implementation.di.MovieSearchEntryModule


@Module(
    includes = [
        MovieSearchEntryModule::class,
        MovieDetailsEntryModule::class,
    ]
)
interface NavigationModule