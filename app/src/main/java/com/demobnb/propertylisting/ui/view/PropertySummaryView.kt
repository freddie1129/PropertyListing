package com.demobnb.propertylisting.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.CurrencyProvider
import com.demobnb.propertylisting.extension.format
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertySummary

@Composable
fun PropertySummaryView(property: PropertySummary,
                        onClick: () -> Unit
                    ) {
    Card (
    elevation = CardDefaults.cardElevation(),
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClick() }
            ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(10.dp)) {
            val placeholderPainter: Painter = rememberVectorPainter(Icons.Default.AccountCircle)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(property.featureImage)
                    .diskCacheKey(property.id.toString())
                    .memoryCacheKey(key = property.id.toString())

                    .build(),
                contentDescription = "Property Image",
                placeholder = placeholderPainter,
                error = placeholderPainter,
                modifier = Modifier.size(120.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = property.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = stringResource(
                    R.string.extendedPrice,
                    CurrencyProvider.symbol,
                    property.extendedPrice.format(),
                    CurrencyProvider.code,
                    property.duration))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Star",
                        modifier = Modifier.size(12.dp)

                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = property.rate.format())
                }
            }

        }
    }
}

@Preview
@Composable
fun PropertySummaryViewPreview() {
    PropertySummaryView(MockData.generateProperties(1)[0], onClick = {

    })
}