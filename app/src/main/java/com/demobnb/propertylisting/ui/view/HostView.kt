package com.demobnb.propertylisting.ui.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.User

@Composable
fun HostView(user: User?) {
    val context = LocalContext.current
    val placeholderPainter: Painter = rememberVectorPainter(Icons.Default.AccountCircle)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user?.avatar)
                .diskCacheKey(user?.id.toString())
                .memoryCacheKey(key = user?.id.toString())
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = placeholderPainter,
            contentDescription = user?.name.orEmpty(),
            modifier = Modifier.size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(8.dp))

        user?.let { user ->
            Column {
                Text(
                    text = stringResource(R.string.hostedBy, user.name),
                    style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold
                )
                val hostingDetail = if (user.hostDurationMonths > 12) {
                    val years = user.hostDurationMonths / 12
                    val yearText =
                        context.resources.getQuantityString(R.plurals.numberOfYear, years.toInt())
                    "$years $yearText"
                } else {
                    val month = user.hostDurationMonths
                    val monthText =
                        context.resources.getQuantityString(R.plurals.numberOfMonth, month.toInt())
                    "$month $monthText"
                }.let { "${user.feature} • $it ${stringResource(R.string.hosting)}" }
                Text(
                    text = hostingDetail,
                    style = MaterialTheme.typography.bodySmall
                )

            }
        }
    }

}

@Preview
@Composable
fun HostViewPreview() {
    HostView(user = MockData.user)
}