package app.simple.movies.feature.moviedetails.implementation.common.reviews.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import app.simple.movies.R
import app.simple.movies.common.domain.model.Review

@Composable
fun Author(review: Review, type: ReviewItemType) {
    val context = LocalContext.current

    val avatarPlaceholderColor = if (review.author.avatarPath != null) {
        Color.Transparent
    } else when (type) {
        ReviewItemType.ODD -> Color.White
        ReviewItemType.EVEN -> Color(0xFFF5F5F5)
    }

    val horizontalAlignment = when (type) {
        ReviewItemType.ODD -> Alignment.Start
        ReviewItemType.EVEN -> Alignment.End
    }

    Row {
        if (type == ReviewItemType.ODD) {
            AsyncImage(
                model = review.author.avatarPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(43.dp)
                    .clip(RoundedCornerShape(50))
                    .background(avatarPlaceholderColor)
            )
            Spacer(Modifier.width(10.dp))
        }
        Column(Modifier.height(43.dp), horizontalAlignment = horizontalAlignment) {
            Text(
                text = review.author.name.ifBlank { context.getString(R.string.anonymous) },
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = review.author.username,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 1
            )
        }
        if (type == ReviewItemType.EVEN) {
            Spacer(Modifier.width(10.dp))
            AsyncImage(
                model = review.author.avatarPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(43.dp)
                    .clip(RoundedCornerShape(50))
                    .background(avatarPlaceholderColor)
            )
        }
    }
}