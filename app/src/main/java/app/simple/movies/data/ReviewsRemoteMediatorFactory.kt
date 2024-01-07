package app.simple.movies.data

import dagger.assisted.AssistedFactory

@AssistedFactory
interface ReviewsRemoteMediatorFactory {

    fun create(movieId: Int): ReviewsRemoteMediator
}