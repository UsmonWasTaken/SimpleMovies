package app.simple.movies.di

import androidx.compose.runtime.staticCompositionLocalOf
import app.simple.movies.entry.Destinations
import app.simple.movies.common.di.CommonProvider
import app.simple.movies.data.DataProvider


interface AppProvider : DataProvider, CommonProvider {

    val destinations: Destinations
}

val LocalAppProvider = staticCompositionLocalOf<AppProvider> { error("No app provider found!") }