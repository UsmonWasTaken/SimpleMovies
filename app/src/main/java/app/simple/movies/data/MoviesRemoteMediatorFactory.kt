package app.simple.movies.data

import dagger.assisted.AssistedFactory

@AssistedFactory
interface MoviesRemoteMediatorFactory {

    fun create(query: String): MoviesRemoteMediator
}