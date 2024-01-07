package app.simple.movies.feature.moviedetails.implementation.credits

import app.simple.movies.common.domain.model.Credit
import app.simple.movies.common.domain.repository.MoviesRepository
import javax.inject.Inject

typealias Department = String

fun interface GetCredits {

    suspend operator fun invoke(movieId: Int): Map<Department, List<Credit>>
}

class GetCreditsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetCredits {

    override suspend fun invoke(movieId: Int): Map<String, List<Credit>> {
        return moviesRepository.getCredits(movieId)
            .sortedBy { it.order }
            .groupBy { it.department }
            .toSortedMap()
    }
}