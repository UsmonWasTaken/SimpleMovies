package app.simple.movies.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import app.simple.movies.common.domain.model.Credit
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.domain.model.Review
import app.simple.movies.common.domain.repository.MoviesRepository
import app.simple.movies.data.mapping.DataMapper
import app.simple.movies.data.network.MovieDbApi
import app.simple.movies.data.storage.AppDatabase
import app.simple.movies.data.storage.dao.MoviesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.net.UnknownHostException
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(
    private val moviesRemoteMediatorFactory: MoviesRemoteMediatorFactory,
    private val reviewsRemoteMediatorFactory: ReviewsRemoteMediatorFactory,
    private val networkApi: MovieDbApi,
    private val mapper: DataMapper,
    private val database: AppDatabase,
) : MoviesRepository {

    override fun getMovies(query: String): Flow<PagingData<Movie>> {
        return createPager(query)
    }

    override fun getTrendingMovies(): Flow<List<Movie>> =
        flow {
            val movies = getTrendingMoviesFromNetwork()
            if (movies != null) emit(movies)
            else emit(getTrendingMoviesFromStorage().orEmpty())
        }.onStart {
            val movies = getTrendingMoviesFromStorage()
            if (!movies.isNullOrEmpty()) emit(movies)
        }.distinctUntilChanged()

    private suspend fun getTrendingMoviesFromNetwork(): List<Movie>? {
        val networkMovies = try {
            networkApi.trendingMovies(MovieDbApi.TIME_WINDOW_WEEK).results
        } catch (e: UnknownHostException) {
            return null
        }
        val domainMovies = networkMovies.map(mapper::networkToDomain)
        val storedMovies = networkMovies.mapIndexed { i, item ->
            mapper.networkToStorage(item, ordinal = i, query = MoviesDao.QUERY_TRENDING)
        }
        database.withTransaction {
            database.moviesDao.deleteByQuery(MoviesDao.QUERY_TRENDING)
            database.moviesDao.insertAll(storedMovies)
        }
        return domainMovies
    }

    private suspend fun getTrendingMoviesFromStorage(): List<Movie>? {
        val storedMovies = database.moviesDao.getTrendingMovies()
        if (storedMovies.isEmpty()) return null
        return storedMovies.map(mapper::storageToDomain)
    }

    override suspend fun getMovieById(id: Int): Movie? {
        val storedMovie = database.moviesDao.getMovieById(id) ?: return null
        return mapper.storageToDomain(storedMovie)
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun createPager(query: String): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = MoviesRepository.PAGE_SIZE,
                enablePlaceholders = true,
            ),
            remoteMediator = moviesRemoteMediatorFactory.create(query),
            pagingSourceFactory = { database.moviesDao.getMovies(query) }
        ).flow.map { pagingData ->
            pagingData.map(mapper::storageToDomain)
        }

    override suspend fun getCredits(movieId: Int): List<Credit> {
        val networkCredits = networkApi.credits(movieId)
        return networkCredits.cast.map(mapper::networkToDomain)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getReviews(movieId: Int): Flow<PagingData<Review>> =
        Pager(
            config = PagingConfig(
                pageSize = MoviesRepository.PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = reviewsRemoteMediatorFactory.create(movieId),
            pagingSourceFactory = { database.reviewsDao.getReviews(movieId) }
        ).flow.map { pagingData ->
            pagingData.map(mapper::storageToDomain)
        }
}
