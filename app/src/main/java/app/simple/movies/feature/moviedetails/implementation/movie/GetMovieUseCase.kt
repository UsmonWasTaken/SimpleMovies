package app.simple.movies.feature.moviedetails.implementation.movie

import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.domain.repository.MoviesRepository
import javax.inject.Inject

fun interface GetMovie {

    suspend operator fun invoke(id: Int): Movie?
}

class GetMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMovie {

    override suspend fun invoke(id: Int): Movie? {
        return moviesRepository.getMovieById(id)
    }
}