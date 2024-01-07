package app.simple.movies.feature.moviedetails.implementation.movie.di

import dagger.Component
import app.simple.movies.common.di.SubfeatureScoped
import app.simple.movies.feature.moviedetails.implementation.di.MovieDetailsComponent
import app.simple.movies.feature.moviedetails.implementation.movie.MovieViewModel

@SubfeatureScoped
@Component(
    dependencies = [MovieDetailsComponent::class],
    modules = [MovieModule::class]
)
interface MovieComponent {
    val viewModel: MovieViewModel
}