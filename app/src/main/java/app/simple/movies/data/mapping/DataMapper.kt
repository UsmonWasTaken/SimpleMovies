package app.simple.movies.data.mapping

import app.simple.movies.common.domain.model.Credit
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.domain.model.Review
import app.simple.movies.data.network.model.MovieDbCredit
import app.simple.movies.data.network.model.MovieDbMovie
import app.simple.movies.data.network.model.MovieDbReview
import app.simple.movies.data.storage.entity.StoredMovie
import app.simple.movies.data.storage.entity.StoredReview

interface DataMapper {

    fun networkToStorage(movie: MovieDbMovie, ordinal: Int, query: String): StoredMovie

    fun networkToStorage(review: MovieDbReview, movieId: Int): StoredReview

    fun networkToDomain(movie: MovieDbMovie): Movie

    fun networkToDomain(credit: MovieDbCredit): Credit

    fun storageToDomain(movie: StoredMovie): Movie

    fun storageToDomain(review: StoredReview): Review
}