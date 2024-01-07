package app.simple.movies.feature.moviesearch.implementation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.simple.movies.common.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieSearchViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    private var query: String? = null
    private var instantRefresh = false
    private val searchQueryFlow = MutableSharedFlow<String?>(replay = 1)
    val displayedSearchQuery: Flow<String> = searchQueryFlow.map { it.orEmpty() }
    private val selectedMovieFlow = MutableStateFlow<Movie?>(value = null)

    val selectedMovie: StateFlow<Movie?> = selectedMovieFlow

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    internal val _movies = searchQueryFlow
        .onEach { query = it }
        .debounce { if (instantRefresh) 0 else QUERY_INPUT_DELAY_MILLIS }
        .onEach { instantRefresh = false }
        .flatMapLatest { getMovies(query) }

    val movies: Flow<PagingData<Movie>> = _movies.cachedIn(viewModelScope)

    fun updateSearchQuery(newQuery: String) {
        val formattedQuery = newQuery.ifBlank { null }
        if (formattedQuery != query) {
            searchQueryFlow.tryEmit(formattedQuery)
        }
    }

    fun refresh() {
        instantRefresh = true
        searchQueryFlow.tryEmit(query)
    }

    fun showMovieDetails(movie: Movie) {
        selectedMovieFlow.value = movie
    }

    fun closeMovieDetails() {
        selectedMovieFlow.value = null
    }

    companion object {

        private const val QUERY_INPUT_DELAY_MILLIS = 1000L
    }
}