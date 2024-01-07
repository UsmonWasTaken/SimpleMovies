package app.simple.movies.feature.moviedetails.implementation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import app.simple.movies.common.di.Dispatcher
import app.simple.movies.common.di.Dispatcher.Name.IO
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.feature.moviedetails.implementation.common.reviews.GetReviews
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewItem
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewsViewModel
import app.simple.movies.feature.moviedetails.implementation.di.MovieId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    @MovieId private val movieId: Int,
    private val getMovie: GetMovie,
    private val getReviews: GetReviews,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), ReviewsViewModel {

    override val reviews: Flow<PagingData<ReviewItem>> by lazy {
        getReviews(movieId)
            .map { it.map { item -> ReviewItem(item) } }
            .cachedIn(viewModelScope)
    }

    private val mutableMovie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = mutableMovie

    fun load() {
        viewModelScope.launch(ioDispatcher) {
            mutableMovie.value = getMovie(movieId)
        }
    }
}