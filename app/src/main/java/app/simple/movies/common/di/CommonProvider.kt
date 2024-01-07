package app.simple.movies.common.di

import android.content.Context
import androidx.compose.runtime.staticCompositionLocalOf
import app.simple.movies.common.di.Dispatcher.Name.IO
import kotlinx.coroutines.CoroutineDispatcher

interface CommonProvider {

    val applicationContext: Context

    @get:Dispatcher(IO)
    val ioDispatcher: CoroutineDispatcher
}

val LocalCommonProvider =
    staticCompositionLocalOf<CommonProvider> { error("No common provider found!") }