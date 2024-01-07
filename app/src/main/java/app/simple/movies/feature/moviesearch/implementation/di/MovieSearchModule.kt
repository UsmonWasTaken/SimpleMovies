package app.simple.movies.feature.moviesearch.implementation.di

import dagger.Binds
import dagger.Module
import app.simple.movies.feature.moviesearch.implementation.GetMovies
import app.simple.movies.feature.moviesearch.implementation.GetMoviesUseCase

@Module
interface MovieSearchModule {

    @Binds
    fun bindSearchMovies(impl: GetMoviesUseCase): GetMovies
}