package com.demobnb.propertylisting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.demobnb.propertylisting.ui.view.PropertySummary


@Composable
fun PropertyListScreen(
    navController: NavController,
    viewModel: PropertyListViewModel = hiltViewModel()
) {
    val items by viewModel.propertyListState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {


        Box(modifier = Modifier.fillMaxSize()) {
            if (uiState.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.error.orEmpty().isNotEmpty()) {
                AlertDialog(
                    onDismissRequest = { viewModel.resetUiState() },
                    title = { Text(stringResource(R.string.error)) },
                    text = { Text(uiState.error.orEmpty()) },
                    confirmButton = {
                        Button(onClick = { viewModel.resetUiState() }) {
                            Text("OK")
                        }
                    }
                )
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    items.forEach { item ->
                        key(item.id) {
                            PropertySummary(property = item, onClick = {
                                navController.navigate("detail/${item.id}")
                            })
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                viewModel.loadData()
            },
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
    // val navController = rememberNavController()
    //  PropertyListScreen(navController = navController)
}
