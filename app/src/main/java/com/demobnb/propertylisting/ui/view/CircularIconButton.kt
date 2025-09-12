package com.demobnb.propertylisting.ui.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demobnb.propertylisting.mock.MockData

@Composable
fun CircularIconButton(onClick : () -> Unit,  content: @Composable () -> Unit, ) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .background(color = Color.White, shape = CircleShape)
            .size(36.dp)
            .padding(2.dp),
        content = content
    )
}

@Preview
@Composable
fun CircularIconButtonPreview() {
    CircularIconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            tint = Color.Black
        )
    }
}