package app.simple.movies.data.di

import app.simple.movies.common.domain.repository.MoviesRepository
import app.simple.movies.data.DefaultMoviesRepository
import app.simple.movies.data.mapping.DataMapper
import app.simple.movies.data.mapping.DefaultDataMapper
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class, StorageModule::class]
)
interface DataModule {

    @Binds
    @Singleton
    fun mapper(impl: DefaultDataMapper): DataMapper

    @Binds
    @Singleton
    fun moviesRepository(impl: DefaultMoviesRepository): MoviesRepository
}