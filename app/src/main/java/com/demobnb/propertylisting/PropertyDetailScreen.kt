package com.demobnb.propertylisting


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.Host
import com.demobnb.propertylisting.ui.view.CircularIconButton
import com.demobnb.propertylisting.ui.view.ExpandableTextView
import com.demobnb.propertylisting.ui.view.HighlightsView
import com.demobnb.propertylisting.ui.view.HostView
import com.demobnb.propertylisting.ui.view.ImageSlider
import com.demobnb.propertylisting.ui.view.ReserveView
import com.demobnb.propertylisting.ui.view.ReviewView
import java.time.LocalDate

@Composable
fun PropertyDetailScreen(itemId: Long,
                         viewModel: PropertyDetailViewModel = hiltViewModel()) {

    val detail by viewModel.propertyDetailState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    detail?.let {  detail ->
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    ImageSlider(
                        imageUrls = detail.featureImages,
                        modifier = Modifier.fillMaxWidth().height(250.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        CircularIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Star",
                                modifier = Modifier.size(12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        CircularIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "Star",
                                modifier = Modifier.size(12.dp)
                            )
                        }
                        CircularIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Share, contentDescription = "Star",
                                modifier = Modifier.size(12.dp)
                            )
                        }



                        CircularIconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Refresh, contentDescription = "Star",
                                modifier = Modifier.size(12.dp)
                            )
                        }

                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = detail.title,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                        )
                        Text(
                            text = detail.address,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        val guestCountString = context.resources.getQuantityString(
                            R.plurals.numberOfGuest,
                            detail.guestCount, detail.guestCount
                        )

                        val bedroomCountString = context.resources.getQuantityString(
                            R.plurals.numberOfBedroom,
                            detail.bedroomCount, detail.bedroomCount
                        )

                        val bedCountString = context.resources.getQuantityString(
                            R.plurals.numberOfBed,
                            detail.bedCount, detail.bedCount
                        )

                        val bathString = context.resources.getQuantityString(
                            R.plurals.numberOfBath,
                            detail.bathCount, detail.bathCount
                        )

                        Text(
                            text = "$guestCountString | $bedroomCountString | $bedCountString | $bathString",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        ReviewView(
                            averageRate = detail.averageRate,
                            reviewStandout = detail.reviewStandout,
                            reviewCount = detail.reviewCount
                        )
                    }

                    HorizontalDivider()

                    HostView(detail.host)

                    HorizontalDivider()

                    HighlightsView(highlights = detail.highlights)

                    HorizontalDivider()

                    Text(
                        text = stringResource(R.string.about_this_place),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    ExpandableTextView(text = detail.introduction)


                    Spacer(modifier = Modifier.height(40.dp))
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            ReserveView(
                price = 123f,
                checkInDate = LocalDate.now(),
                checkOutDate = LocalDate.now().plusDays(133),
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
                onClick = {
                }
            )
        }
    }

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
    PropertyDetailScreen(itemId = 10)
}
