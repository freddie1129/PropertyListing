package com.demobnb.propertylisting.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Carousel


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.formatDuration
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import java.time.LocalDate

@Composable
fun CommentView(review: Review, modifier: Modifier) {
    Column(modifier = modifier.height(100.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(review.rating) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Star",
                    modifier = Modifier.size(8.dp)
                )
            }
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = formatDuration(review.createAt, LocalDate.now()) + " " + stringResource(R.string.ago),
                style = MaterialTheme.typography.bodySmall)

        }

        Text(text = review.comment,
            maxLines = 4)

        Spacer(modifier = Modifier.weight(1f))

        CommentUserView(userId = review.userId)


    }
}

@Composable
fun CommentUserView(userId: Long) {

    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(userId) {   // 👈 Runs when userId changes
        user = MockData.generateUser(userId)
    }
    val placeholderPainter: Painter = rememberVectorPainter(Icons.Default.AccountCircle)


    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user?.avatar)
                .build(),
            placeholder = placeholderPainter,
            error = placeholderPainter,
            contentDescription = user?.name,
            modifier = Modifier.size(24.dp)
                .clip(CircleShape)
        )
        user?.let { user ->
            Column {

                Text(text = user.name.orEmpty())
                Text(text = formatDuration(user.createdAt, LocalDate.now()) + " " + stringResource(R.string.onAirbnb))
            }
        }
    }
}

@Preview
@Composable
fun CommentViewPreview() {
    val review = Review(
        id = 1,
        propertyId = 12L,
        userId = 12,
        rating = 4,
        comment = "This is a comment",
        createAt = LocalDate.now().minusDays(10)
    )
    CommentView(review, Modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselDemo(items: List<Review>) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.size },
        modifier = Modifier
            .wrapContentHeight()
            .height(400.dp),
        preferredItemWidth = 600.dp
    ) { page ->

        CommentView(items[page], modifier = Modifier.height(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CarouselDemoPreview() {
    val reviews =  Array(10) { Review(
        id = 1,
        propertyId = 12L,
        userId = 12,
        rating = 4,
        comment = "This is a comment",
        createAt = LocalDate.now().minusDays(10)
    )}.toList()
    CarouselDemo(reviews)
}
