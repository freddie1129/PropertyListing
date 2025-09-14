package com.demobnb.propertylisting.ui.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.format

@Composable
fun ReviewSummaryView(averageRate: Float, reviewStandout: String, reviewCount: Int) {
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
        VerticalDivider(
            modifier = Modifier
                .height(24.dp) // make divider span row height
                .width(1.dp)     // divider thickness
        )

        Row(
            modifier = Modifier.weight(1.5f),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(painter = painterResource(id = R.drawable.outline_clear_all_24),
                contentDescription = stringResource(R.string.reviewStandout),
                modifier = Modifier.graphicsLayer(
                    scaleX = -1f
                ))
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = reviewStandout.replace(" ", "\n"),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 16.sp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(painter = painterResource(id = R.drawable.outline_clear_all_24),
                contentDescription = stringResource(R.string.reviewStandout))
            Spacer(modifier = Modifier.weight(1f))

        }

        VerticalDivider(
            modifier = Modifier
                .height(24.dp) // make divider span row height
                .width(1.dp)     // divider thickness
        )

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
            Text(text = reviewString,
                style = MaterialTheme.typography.bodySmall)
        }
    }

}

@Preview
@Composable
fun ReviewViewPreview() {
    ReviewSummaryView(averageRate = 8f, reviewStandout = "Guest favourite", reviewCount = 12)
}