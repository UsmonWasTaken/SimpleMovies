package app.simple.movies.feature.moviedetails.implementation.di

import dagger.Binds
import dagger.Module
import app.simple.movies.feature.moviedetails.implementation.movie.GetMovie
import app.simple.movies.feature.moviedetails.implementation.movie.GetMovieUseCase

@Module
interface MovieDetailsModule {

    @Binds
    fun bindGetMovies(impl: GetMovieUseCase): GetMovie
}