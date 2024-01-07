package app.simple.movies.feature.moviedetails.implementation.common.reviews.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.simple.movies.R
import app.simple.movies.common.ui.Dimens
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewItem
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewItemState.COLLAPSED
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ReviewItemState.EXPANDED
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ui.ReviewItemType.EVEN
import app.simple.movies.feature.moviedetails.implementation.common.reviews.ui.ReviewItemType.ODD

@Composable
fun ReviewItem(reviewItem: ReviewItem, type: ReviewItemType) {
    val review = reviewItem.review
    val state = reviewItem.state

    val context = LocalContext.current

    val showContentVisibilityButton by remember {
        mutableStateOf(review.content.length > COLLAPSED_CONTENT_LENGTH_LIMIT)
    }
    val content by remember(state.value) {
        val value = when (reviewItem.state.value) {
            COLLAPSED -> if (review.content.length <= COLLAPSED_CONTENT_LENGTH_LIMIT) {
                review.content
            } else {
                review.content.substring(0 until COLLAPSED_CONTENT_LENGTH_LIMIT) + "..."
            }

            EXPANDED -> review.content
        }
        mutableStateOf(value)
    }

    val modifier = when (type) {
        ODD -> Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5))
            .padding(end = 40.dp)

        EVEN -> Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(start = 40.dp)
    }

    val horizontalAlignment = when (type) {
        ODD -> Alignment.Start
        EVEN -> Alignment.End
    }
    Column(
        modifier
            .padding(horizontal = Dimens.horizontalPadding, vertical = 16.dp)
            .padding(end = 5.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        Author(review, type)
        Spacer(Modifier.height(10.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.animateContentSize()
        )
        if (showContentVisibilityButton) {
            Spacer(Modifier.height(10.dp))
            Text(
                text = when (state.value) {
                    COLLAPSED -> context.getString(R.string.show_more)
                    EXPANDED -> context.getString(R.string.show_less)
                },
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    state.value = when (state.value) {
                        COLLAPSED -> EXPANDED
                        EXPANDED -> COLLAPSED
                    }
                }
            )
        }
    }
}