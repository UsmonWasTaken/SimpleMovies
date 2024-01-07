package app.simple.movies.feature.moviedetails.implementation.common.reviews.di

import dagger.Binds
import dagger.Module
import app.simple.movies.feature.moviedetails.implementation.common.reviews.GetReviews
import app.simple.movies.feature.moviedetails.implementation.common.reviews.GetReviewsUseCase

@Module
interface ReviewsModule {

    @Binds
    fun bindGetReviews(impl: GetReviewsUseCase): GetReviews
}