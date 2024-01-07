package app.simple.movies.feature.moviedetails.implementation.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import app.simple.movies.common.di.FeatureEntryKey
import app.simple.movies.entry.FeatureEntry
import app.simple.movies.feature.moviedetails.entry.MovieDetailsEntry
import app.simple.movies.feature.moviedetails.implementation.MovieDetailsEntryImpl
import javax.inject.Singleton

@Module
interface MovieDetailsEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(MovieDetailsEntry::class)
    fun movieSearchEntry(entry: MovieDetailsEntryImpl): FeatureEntry
}