package app.simple.movies.feature.moviesearch.implementation

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import app.simple.movies.common.di.Dispatcher
import app.simple.movies.common.di.Dispatcher.Name.IO
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

fun interface GetMovies {

    operator fun invoke(query: String?): Flow<PagingData<Movie>>
}

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : GetMovies {

    override fun invoke(query: String?): Flow<PagingData<Movie>> {
        val movies = if (query.isNullOrBlank()) getTrendingMovies() else searchMovies(query)
        return movies.flowOn(ioDispatcher)
    }

    internal fun getTrendingMovies(): Flow<PagingData<Movie>> =
        moviesRepository.getTrendingMovies().map { movies ->
            val state = LoadState.NotLoading(endOfPaginationReached = true)
            val states = LoadStates(state, state, state)
            PagingData.from(movies, states)
        }.onStart {
            val state = LoadState.Loading
            val states = LoadStates(state, state, state)
            emit(PagingData.empty(states))
        }

    internal fun searchMovies(query: String): Flow<PagingData<Movie>> =
        moviesRepository.getMovies(query)
}