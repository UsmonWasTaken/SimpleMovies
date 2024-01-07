package app.simple.movies.feature.moviedetails.implementation.credits.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import app.simple.movies.R
import app.simple.movies.common.domain.model.Credit
import app.simple.movies.feature.moviedetails.implementation.credits.CreditsViewModel

private sealed interface State {
    val title: String

    class NoCredits(override val title: String) : State

    class Credits(
        override val title: String,
        val credits: Map<String, List<Credit>>
    ) : State
}

@Composable
fun CreditsScreen(viewModel: CreditsViewModel) {
    LaunchedEffect(Unit) { viewModel.load() }

    val movieTitle by viewModel.movieTitle.collectAsState()
    val credits by viewModel.credits.collectAsState()

    val context = LocalContext.current

    val state = remember(movieTitle == null, credits.isEmpty()) {
        val title = movieTitle ?: context.getString(R.string.credits)
        when (credits.size) {
            0 -> State.NoCredits(title)
            else -> State.Credits(title, credits)
        }
    }

    RenderState(state)
}

@Composable
private fun RenderState(state: State) {
    Column {
        Toolbar(state.title)

        when (state) {
            is State.Credits -> CreditsList(state.credits)
            is State.NoCredits -> NoCredits()
        }
    }
}
