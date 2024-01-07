package app.simple.movies.feature.moviedetails.implementation.credits.di

import dagger.Binds
import dagger.Module
import app.simple.movies.feature.moviedetails.implementation.credits.GetCredits
import app.simple.movies.feature.moviedetails.implementation.credits.GetCreditsUseCase


@Module
interface CreditsModule {

    @Binds
    fun bindGetSortedCredits(impl: GetCreditsUseCase): GetCredits
}