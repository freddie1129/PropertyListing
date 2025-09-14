package com.demobnb.propertylisting


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import com.demobnb.propertylisting.ui.view.ReviewsRowView
import com.demobnb.propertylisting.ui.view.CircularIconButton
import com.demobnb.propertylisting.ui.view.ExpandableTextView
import com.demobnb.propertylisting.ui.view.HighlightsView
import com.demobnb.propertylisting.ui.view.HostView
import com.demobnb.propertylisting.ui.view.ImageSlider
import com.demobnb.propertylisting.ui.view.ReserveView
import com.demobnb.propertylisting.ui.view.ReviewSummaryView
import com.demobnb.propertylisting.ui.view.UIStateScreen
import java.time.LocalDate


@Composable
fun PropertyDetailScreen(
                         paddingValues: PaddingValues,
                         navController: NavController,
                         viewModel: PropertyDetailViewModel = hiltViewModel()) {

    val detail by viewModel.propertyDetailState.collectAsState()
    val user by viewModel.propertyUserState.collectAsState()
    val reviews by viewModel.propertyReviewState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    UIStateScreen(uiState = uiState, onDismissAlert = {
        viewModel.resetUiState()
    }) {

        detail?.let {  detail ->
            PropertyDetailScreenContentView(detail,
                paddingValues = paddingValues,
                user = user,
                reviews = reviews,
                onBack = {
                navController.popBackStack()
            })
        }



    }



}

@Composable
fun PropertyDetailScreenContentView(detail: PropertyDetail,
                                    user: User?,
                                    reviews: List<Review>,
                                    paddingValues: PaddingValues,
                                    onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
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
                        .padding(top = paddingValues.calculateTopPadding())
                ) {
                    CircularIconButton(onClick = onBack) {
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
                    ReviewSummaryView(
                        averageRate = detail.averageRate,
                        reviewStandout = detail.reviewStandout,
                        reviewCount = detail.reviewCount
                    )
                }

                HorizontalDivider()


                HostView(user)


                HorizontalDivider()

                HighlightsView(highlights = detail.highlights)

                HorizontalDivider()

                Text(
                    text = stringResource(R.string.about_this_place),
                    style = MaterialTheme.typography.bodyLarge
                )
                ExpandableTextView(text = detail.introduction)

                HorizontalDivider()

                ReviewsRowView(reviews)


                Spacer(modifier = Modifier.height(80.dp))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        ReserveView(
            price = 123f,
            checkInDate = LocalDate.now(),
            checkOutDate = LocalDate.now().plusDays(133),
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(bottom = paddingValues.calculateBottomPadding()),
            onClick = {
            }
        )
    }
}





@Preview
@Composable
fun PropertyDetailScreenPreview() {
    PropertyDetailScreenContentView(detail = MockData.generatePropertyDetail(2),
        paddingValues = PaddingValues(),
        user = MockData.generateUser(12),
        reviews = MockData.generateReviews(10),
        onBack = { })
}
