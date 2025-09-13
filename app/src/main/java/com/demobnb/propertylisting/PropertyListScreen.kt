package com.demobnb.propertylisting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.ui.view.PropertySummaryView
import com.demobnb.propertylisting.ui.view.UIStateScreen


@Composable
fun PropertyListScreen(
    navController: NavController,
    viewModel: PropertyListViewModel = hiltViewModel()
) {
    val items by viewModel.propertyListState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    UIStateScreen(uiState = uiState, onDismissAlert = {
        viewModel.resetUiState()
    }) {
        PropertyListScreenContentView(items = items, uiState = uiState,
            onRefresh = {viewModel.loadData()}, onPropertyClick = {
                navController.navigate("detail/$it")
            })
    }
}

@Composable
fun PropertyListScreenContentView(items: List<PropertySummary>,
                                  uiState: UiState,
                                  onRefresh: () -> Unit,
                                  onPropertyClick: (Long) -> Unit) {
    val scrollState = rememberScrollState()
    Box {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            items.forEach { item ->
                key(item.id) {
                    PropertySummaryView(property = item, onClick =  {
                        onPropertyClick(item.id)

                    })
                }
            }
        }

        Button(
            onClick = onRefresh,
            enabled = !uiState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)

        ) {
            Text(text = stringResource(R.string.refresh))
        }
    }
}




@Preview
@Composable
fun PropertyListScreenPreview() {
     PropertyListScreenContentView(items = MockData.generateProperties(10), uiState = UiState(), onRefresh = {}, onPropertyClick = {})
}
