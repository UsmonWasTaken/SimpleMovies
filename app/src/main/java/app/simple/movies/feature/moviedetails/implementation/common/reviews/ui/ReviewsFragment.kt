package app.simple.movies.feature.moviedetails.implementation.common.reviews.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import app.simple.movies.common.ui.Dimens.horizontalPadding
import app.simple.movies.R
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewItem
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewsViewModel

private sealed interface State {

    object EmptyFeed : State

    class FeedWithContent(val reviews: LazyPagingItems<ReviewItem>) : State
}

@Composable
fun ReviewsFragment(viewModel: ReviewsViewModel, header: @Composable () -> Unit) {
    val reviews = viewModel.reviews.collectAsLazyPagingItems()

    val state = remember(reviews.itemCount == 0) {
        when (reviews.itemCount) {
            0 -> State.EmptyFeed
            else -> State.FeedWithContent(reviews)
        }
    }

    RenderState(state, header)
}

@Composable
private fun RenderState(state: State, header: @Composable () -> Unit) {
    Column {
        when (state) {
            is State.EmptyFeed -> {
                Header(header)
                NoReviews()
            }

            is State.FeedWithContent -> ReviewList(
                state.reviews,
                header = { Header(header) })
        }
    }
}

@Composable
private fun Header(header: @Composable () -> Unit) {
    val context = LocalContext.current

    header()
    Text(
        context.getString(R.string.reviews),
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = horizontalPadding)
    )
    Spacer(Modifier.height(10.dp))
}