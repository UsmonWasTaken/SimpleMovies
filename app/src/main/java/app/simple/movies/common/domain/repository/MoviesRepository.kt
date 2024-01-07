package app.simple.movies.common.domain.repository

import androidx.paging.PagingData
import app.simple.movies.common.domain.model.Credit
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(query: String): Flow<PagingData<Movie>>

    fun getTrendingMovies(): Flow<List<Movie>>

    suspend fun getMovieById(id: Int): Movie?

    suspend fun getCredits(movieId: Int): List<Credit>

    fun getReviews(movieId: Int): Flow<PagingData<Review>>

    companion object {
        const val PAGE_SIZE = 20
    }
}