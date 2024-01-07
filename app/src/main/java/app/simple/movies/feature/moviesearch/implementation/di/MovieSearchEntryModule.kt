package app.simple.movies.feature.moviesearch.implementation.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import app.simple.movies.common.di.FeatureEntryKey
import app.simple.movies.entry.FeatureEntry
import app.simple.movies.feature.moviesearch.entry.MovieSearchEntry
import app.simple.movies.feature.moviesearch.implementation.MovieSearchEntryImpl
import javax.inject.Singleton

@Module
interface MovieSearchEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(MovieSearchEntry::class)
    fun movieSearchEntry(entry: MovieSearchEntryImpl): FeatureEntry
}