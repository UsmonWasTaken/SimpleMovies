package app.simple.movies.feature.moviedetails.implementation.movie.di

import dagger.Module
import app.simple.movies.feature.moviedetails.implementation.common.reviews.di.ReviewsModule

@Module(includes = [ReviewsModule::class])
interface MovieModule