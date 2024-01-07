package app.simple.movies.feature.moviedetails.implementation.di

import dagger.BindsInstance
import dagger.Component
import app.simple.movies.common.di.CommonProvider
import app.simple.movies.common.di.FeatureScoped
import app.simple.movies.data.DataProvider
import app.simple.movies.feature.moviedetails.entry.MovieDetailsProvider
import app.simple.movies.feature.moviedetails.implementation.movie.GetMovie

@FeatureScoped
@Component(
    dependencies = [DataProvider::class, CommonProvider::class],
    modules = [MovieDetailsModule::class]
)
interface MovieDetailsComponent : MovieDetailsProvider, DataProvider, CommonProvider {
    val getMovie: GetMovie

    @get:MovieId
    val movieId: Int

    @Component.Factory
    interface Factory {

        fun create(
            dataProvider: DataProvider,
            commonProvider: CommonProvider,
            @BindsInstance @MovieId movieId: Int
        ): MovieDetailsComponent
    }
}