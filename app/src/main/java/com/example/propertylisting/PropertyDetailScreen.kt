package com.example.propertylisting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PropertyDetailScreen(itemId: String?) {

    Text(text = "Detail of item: $itemId")
}

@Composable
fun SampleScreen() {
    val images = listOf(
        "https://picsum.photos/id/237/400/300",
        "https://picsum.photos/id/238/400/300",
        "https://picsum.photos/id/239/400/300"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageSlider(imageUrls = images, modifier = Modifier.fillMaxWidth().height(250.dp))
        Spacer(modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun PropertyDetailScreenPreview() {
    PropertyDetailScreen(itemId = "123")
}
