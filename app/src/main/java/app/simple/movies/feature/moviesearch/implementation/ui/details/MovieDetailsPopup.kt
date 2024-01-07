package app.simple.movies.feature.moviesearch.implementation.ui.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.simple.movies.common.domain.model.Movie
import app.simple.movies.common.ui.Dimens.bottomPadding
import app.simple.movies.common.ui.theme.AppBlackTransparent
import app.simple.movies.feature.moviesearch.implementation.ui.details.State.Displayed
import app.simple.movies.feature.moviesearch.implementation.ui.details.State.PostDisplayed
import app.simple.movies.feature.moviesearch.implementation.ui.details.State.PreDisplayed

private enum class State {
    PreDisplayed, Displayed, PostDisplayed
}

@Composable
fun MovieDetailsPopup(
    movie: Movie,
    onClosed: () -> Unit,
    onExpandMovieDetails: (Movie) -> Unit
) {
    var state by remember { mutableStateOf(PreDisplayed) }
    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            PreDisplayed, PostDisplayed -> Color.Transparent
            Displayed -> AppBlackTransparent
        },
        animationSpec = tween(300),
        finishedListener = {
            if (state == PostDisplayed) onClosed()
        }
    )
    val offset by animateDpAsState(
        targetValue = when (state) {
            PreDisplayed, PostDisplayed -> 1000.dp
            Displayed -> 0.dp
        },
        animationSpec = tween(300)
    )

    LaunchedEffect(Unit) {
        state = Displayed
    }

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        Modifier.clickable(interactionSource, null) { state = PostDisplayed },
        contentAlignment = Alignment.BottomCenter
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
        )
        MovieDetailsCard(
            movie,
            Modifier
                .offset(y = offset)
                .padding(bottom = bottomPadding)
                .clickable(interactionSource, null) {},
            onClose = { state = PostDisplayed },
            onExpandMovieDetails
        )
    }
}