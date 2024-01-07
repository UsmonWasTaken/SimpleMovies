package app.simple.movies.data.mapping

import app.simple.movies.common.domain.model.Author
import app.simple.movies.common.domain.model.Credit
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.domain.model.Review
import app.simple.movies.data.network.MovieDbApi
import app.simple.movies.data.network.model.MovieDbAuthor
import app.simple.movies.data.network.model.MovieDbCredit
import app.simple.movies.data.network.model.MovieDbMovie
import app.simple.movies.data.network.model.MovieDbReview
import app.simple.movies.data.storage.entity.StoredAuthor
import app.simple.movies.data.storage.entity.StoredMovie
import app.simple.movies.data.storage.entity.StoredReview
import java.math.RoundingMode
import javax.inject.Inject

class DefaultDataMapper @Inject constructor() : DataMapper {

    override fun networkToStorage(movie: MovieDbMovie, ordinal: Int, query: String) = with(movie) {
        StoredMovie(
            localId = query + "_" + id,
            id = id,
            ordinal = ordinal,
            query = query,
            title = title,
            overview = overview,
            backdropPath = backdropPath,
            posterPath = posterPath,
            voteAverage = voteAverage.rounded(),
            voteCount = voteCount
        )
    }

    override fun networkToStorage(review: MovieDbReview, movieId: Int) = with(review) {
        StoredReview(
            id = id,
            movieId = movieId,
            author = networkToStorage(authorDetails),
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun networkToStorage(author: MovieDbAuthor) = with(author) {
        StoredAuthor(
            name = name,
            username = username,
            avatarPath = avatarPath,
            rating = rating
        )
    }

    override fun networkToDomain(movie: MovieDbMovie) = with(movie) {
        Movie(
            id = id,
            title = title,
            overview = overview,
            thumbPosterUrl = buildPosterUrl(posterPath, isThumb = true),
            posterUrl = buildPosterUrl(posterPath),
            thumbBackdropUrl = buildBackdropUrl(backdropPath, isThumb = true),
            backdropUrl = buildBackdropUrl(backdropPath),
            voteAverage = voteAverage.rounded(),
            voteCount = voteCount
        )
    }

    override fun networkToDomain(credit: MovieDbCredit) = with(credit) {
        Credit(
            id = id,
            name = name,
            character = character,
            department = knownForDepartment,
            profilePath = profilePath,
            order = order
        )
    }

    override fun storageToDomain(movie: StoredMovie) = with(movie) {
        Movie(
            id = id,
            title = title,
            overview = overview,
            thumbPosterUrl = buildPosterUrl(posterPath, isThumb = true),
            posterUrl = buildPosterUrl(posterPath),
            thumbBackdropUrl = buildBackdropUrl(backdropPath, isThumb = true),
            backdropUrl = buildBackdropUrl(backdropPath),
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }

    private fun Float.rounded(): Float =
        toBigDecimal().setScale(1, RoundingMode.UP).toFloat()

    override fun storageToDomain(review: StoredReview) = with(review) {
        Review(
            id = id,
            author = storageToDomain(author),
            content = content,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun storageToDomain(author: StoredAuthor) = with(author) {
        Author(
            name = name,
            username = username,
            avatarPath = buildProfileUrl(avatarPath),
            rating = rating
        )
    }

    internal fun buildPosterUrl(path: String?, isThumb: Boolean = false): String? {
        if (path == null) return null
        val resolution = if (isThumb) "w92" else "w500"
        return "${MovieDbApi.IMAGES_BASE_URL}$resolution${path}"
    }

    internal fun buildBackdropUrl(path: String?, isThumb: Boolean = false): String? {
        if (path == null) return null
        val resolution = if (isThumb) "w300" else "original"
        return "${MovieDbApi.IMAGES_BASE_URL}$resolution${path}"
    }

    internal fun buildProfileUrl(path: String?): String? {
        if (path == null) return null
        if (path.startsWith("http")) return path
        if (path.startsWith("/http")) return path.substring(1)
        val resolution = "w185"
        return "${MovieDbApi.IMAGES_BASE_URL}${resolution}${path}"
    }
}