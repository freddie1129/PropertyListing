package com.demobnb.propertylisting.ui.view

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.formatDuration
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.User
import com.demobnb.propertylisting.model.Review
import java.time.LocalDate

@Composable
fun ReviewView(review: Review, modifier: Modifier) {
    var user by remember { mutableStateOf<User?>(null) }
    LaunchedEffect(review.userId) {
        // TODO fetch user from server through repository
        user = MockData.generateUser(review.userId)
    }
    ReviewContentView(review, user, modifier)
}

@Composable
fun ReviewContentView(review: Review, user: User?, modifier: Modifier) {
    Card {
        Column(modifier = modifier.height(200.dp)
            .width(150.dp)
            .padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(review.rating) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Star",
                        modifier = Modifier.size(8.dp)
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = formatDuration(
                        review.createAt,
                        LocalDate.now()
                    ) + " " + stringResource(R.string.ago),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp
                )

            }

            Text(
                text = review.comment,
                maxLines = 4
            )

            Spacer(modifier = Modifier.weight(1f))

            user?.let {
                ReviewUserView(it)

            }


        }
    }
}

@Composable
fun ReviewUserView(user: User) {




    val placeholderPainter: Painter = rememberVectorPainter(Icons.Default.AccountCircle)


    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatar)
                .build(),
            placeholder = placeholderPainter,
            error = placeholderPainter,
            contentDescription = user.name,
            modifier = Modifier.size(24.dp)
                .clip(CircleShape)
        )
        user?.let { user ->
            Column {

                Text(text = user.name.orEmpty(),
                    style = MaterialTheme.typography.bodySmall)
                Text(text = formatDuration(user.createdAt, LocalDate.now()) + " " + stringResource(R.string.onAirbnb),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp)
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsRowView(items: List<Review>) {

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()))  {
        for (review in items) {
            ReviewView(review, modifier = Modifier.height(200.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ReviewContentViewPreview() {
    val review = MockData.generateReview(1, 12, 12)
    val user = MockData.generateUser(12)
    ReviewContentView(review, user, Modifier)
}
