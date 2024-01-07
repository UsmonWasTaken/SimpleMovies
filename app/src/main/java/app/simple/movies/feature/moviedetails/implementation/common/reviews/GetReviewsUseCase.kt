package app.simple.movies.feature.moviedetails.implementation.common.reviews

import androidx.paging.PagingData
import app.simple.movies.common.di.Dispatcher
import app.simple.movies.common.di.Dispatcher.Name.IO
import app.simple.movies.common.domain.model.Review
import app.simple.movies.common.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

fun interface GetReviews {

    operator fun invoke(movieId: Int): Flow<PagingData<Review>>
}

class GetReviewsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : GetReviews {

    override fun invoke(movieId: Int): Flow<PagingData<Review>> {
        return moviesRepository.getReviews(movieId).flowOn(ioDispatcher)
    }
}