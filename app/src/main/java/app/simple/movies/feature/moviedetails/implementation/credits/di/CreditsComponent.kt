package app.simple.movies.feature.moviedetails.implementation.credits.di

import dagger.Component
import app.simple.movies.common.di.SubfeatureScoped
import app.simple.movies.feature.moviedetails.implementation.credits.CreditsViewModel
import app.simple.movies.feature.moviedetails.implementation.di.MovieDetailsComponent


@SubfeatureScoped
@Component(
    dependencies = [MovieDetailsComponent::class],
    modules = [CreditsModule::class]
)
interface CreditsComponent {
    val viewModel: CreditsViewModel
}