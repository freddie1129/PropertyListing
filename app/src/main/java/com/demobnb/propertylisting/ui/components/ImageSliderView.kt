package com.demobnb.propertylisting.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun ImageSliderView(
    imageUrls: List<String>,
    modifier: Modifier = Modifier.Companion
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    rememberCoroutineScope()


    Box(modifier = modifier
        .fillMaxWidth()
        .height(250.dp)
        .background(Color.Companion.Gray)) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.Companion
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->

            AsyncImage(
                model = imageUrls[page],
                contentDescription = "Image $page",
                modifier = Modifier.Companion.fillMaxSize(),
                contentScale = ContentScale.Companion.Crop
            )


        }

        Text(
            text = "${pagerState.currentPage + 1}/${imageUrls.size}",
            fontSize = 16.sp,
            color = Color.Companion.White,
            modifier = Modifier.Companion
                .align(Alignment.Companion.BottomEnd) // 👈 place bottom-right
                .padding(8.dp)
                .background(Color.Companion.Gray.copy(alpha = 0.6f))
                .padding(horizontal = 4.dp, vertical = 2.dp)
        )


    }
}