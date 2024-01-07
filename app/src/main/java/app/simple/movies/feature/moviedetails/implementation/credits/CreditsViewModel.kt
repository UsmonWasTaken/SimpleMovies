package app.simple.movies.feature.moviedetails.implementation.credits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.simple.movies.common.di.Dispatcher
import app.simple.movies.common.di.Dispatcher.Name.IO
import app.simple.movies.common.domain.model.Credit
import app.simple.movies.feature.moviedetails.implementation.di.MovieId
import app.simple.movies.feature.moviedetails.implementation.movie.GetMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreditsViewModel @Inject constructor(
    @MovieId private val movieId: Int,
    private val getMovie: GetMovie,
    private val getCredits: GetCredits,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val mutableCredits = MutableStateFlow<Map<Department, List<Credit>>>(emptyMap())
    val credits: StateFlow<Map<Department, List<Credit>>> = mutableCredits

    private val mutableMovieTitle = MutableStateFlow<String?>(null)
    val movieTitle: StateFlow<String?> = mutableMovieTitle

    fun load() {
        viewModelScope.launch(ioDispatcher) {
            val movie = async { getMovie(movieId) }
            val credits = async { getCredits(movieId) }

            mutableMovieTitle.value = movie.await()?.title
            mutableCredits.value = credits.await()
        }
    }
}