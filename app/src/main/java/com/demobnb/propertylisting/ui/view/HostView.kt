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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.format
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.Host

@Composable
fun HostView(host: Host) {
    val context = LocalContext.current
    val placeholderPainter: Painter = rememberVectorPainter(Icons.Default.AccountCircle)
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(host.avatar)
                .diskCacheKey(host.id.toString())
                .memoryCacheKey(key = host.id.toString())
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = placeholderPainter,
            contentDescription = host.name,
            modifier = Modifier.size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(8.dp))

        Column {
            Text(text = stringResource(R.string.hostedBy, host.name),
                style = MaterialTheme.typography.titleSmall ,fontWeight = FontWeight.Bold)
            val hostingDetail = if (host.hostDurationMonths > 12) {
                val years = host.hostDurationMonths / 12
                val yearText = context.resources.getQuantityString(R.plurals.numberOfYear, years.toInt())
                "$years $yearText"
            } else {
                val month = host.hostDurationMonths
                val monthText = context.resources.getQuantityString(R.plurals.numberOfMonth, month.toInt())
                "$month $monthText"
            }.let { "${host.feature} • $it ${stringResource(R.string.hosting)}"}
            Text(text = hostingDetail,
                style = MaterialTheme.typography.bodySmall)

        }
    }

}

@Preview
@Composable
fun HostViewPreview() {
    HostView(host = MockData.host)
}