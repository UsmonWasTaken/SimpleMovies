package app.simple.movies.feature.moviesearch.implementation.di

import dagger.Component
import app.simple.movies.common.di.CommonProvider
import app.simple.movies.common.di.FeatureScoped
import app.simple.movies.data.DataProvider
import app.simple.movies.feature.moviesearch.entry.MovieSearchProvider
import app.simple.movies.feature.moviesearch.implementation.MovieSearchViewModel


@FeatureScoped
@Component(
    dependencies = [DataProvider::class, CommonProvider::class],
    modules = [MovieSearchModule::class]
)
interface MovieSearchComponent : MovieSearchProvider {

    val viewModel: MovieSearchViewModel
}