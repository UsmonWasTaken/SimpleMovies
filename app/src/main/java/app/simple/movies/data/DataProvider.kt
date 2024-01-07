package app.simple.movies.data

import androidx.compose.runtime.staticCompositionLocalOf
import app.simple.movies.common.domain.repository.MoviesRepository

interface DataProvider {

    val moviesRepository: MoviesRepository
}

val LocalDataProvider = staticCompositionLocalOf<DataProvider> { error("No data provider found!") }