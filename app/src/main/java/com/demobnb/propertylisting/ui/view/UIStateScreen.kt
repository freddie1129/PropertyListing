package com.demobnb.propertylisting.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.UiState

@Composable
fun UIStateScreen(uiState: UiState, onDismissAlert: () -> Unit, content: @Composable () -> Unit) {
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
            Text("testerror")
            AlertDialog(
                onDismissRequest = onDismissAlert,
                title = { Text(stringResource(R.string.error)) },
                text = { Text(uiState.error.orEmpty()) },
                confirmButton = {
                    Button(onClick = onDismissAlert) {
                        Text("OK")
                    }
                }
            )
        } else {

            content()
        }
    }
}


@Preview
@Composable
fun UIStateScreenPreview() {
    UIStateScreen(uiState = UiState(), onDismissAlert = {}) {
        Text("test")
    }
}