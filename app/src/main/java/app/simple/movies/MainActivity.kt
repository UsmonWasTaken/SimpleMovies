package app.simple.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import app.simple.movies.common.di.LocalCommonProvider
import app.simple.movies.common.ui.NavigationBar
import app.simple.movies.common.ui.StatusBar
import app.simple.movies.common.ui.theme.AppBlack
import app.simple.movies.common.ui.theme.SimpleMoviesTheme
import app.simple.movies.data.LocalDataProvider
import app.simple.movies.di.LocalAppProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleMoviesTheme {
                StatusBar(window, color = MaterialTheme.colors.background)

                Surface(color = MaterialTheme.colors.background) {
                    CompositionLocalProvider(
                        LocalCommonProvider provides application.appProvider,
                        LocalDataProvider provides application.appProvider,
                        LocalAppProvider provides application.appProvider,
                    ) {
                        Navigation()
                    }
                }

                NavigationBar(window, color = AppBlack)
            }
        }
    }
}