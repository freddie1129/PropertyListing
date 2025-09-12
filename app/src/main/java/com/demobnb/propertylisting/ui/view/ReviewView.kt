package com.demobnb.propertylisting.ui.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.format
import com.demobnb.propertylisting.mock.MockData

@Composable
fun ReviewView(averageRate: Float, reviewStandout: String, reviewCount: Int) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = averageRate.format(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                repeat(averageRate.toInt()) {
                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "Star",
                        modifier = Modifier.size(8.dp)
                    )
                }
            }

        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.Favorite, contentDescription = "Favorite")
            Text(
                text = reviewStandout.replace(" ", "\n"),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 16.sp)
            )
            Icon(Icons.Default.Favorite, contentDescription = "Favorite")
            Spacer(modifier = Modifier.weight(1f))

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = reviewCount.toString(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 16.sp)
            )
            val reviewString =
                context.resources.getQuantityString(R.plurals.numberOfReview, reviewCount)
            Text(text = reviewString)
        }
    }

}

@Preview
@Composable
fun ReviewViewPreview() {
    ReviewView(averageRate = 8f, reviewStandout = "Guest favourite", reviewCount = 12)
}