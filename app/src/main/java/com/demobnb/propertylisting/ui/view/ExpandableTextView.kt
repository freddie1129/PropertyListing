package com.demobnb.propertylisting.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.constant.TestTags
import com.demobnb.propertylisting.mock.MockData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableTextView(text: String, modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var hasOverflow by remember { mutableStateOf(false) }

    var showSheet by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = text,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            onTextLayout = { textLayoutResult: TextLayoutResult ->

                hasOverflow = textLayoutResult.hasVisualOverflow

            }
        )




        if (hasOverflow) {
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = { showSheet = true },
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth().padding(16.dp),
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Gray.copy(alpha = 0.6f),
                    contentColor = Color.Black

                )


            ) {
                Text(stringResource(R.string.show_more))
            }
        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_bottom_sheet),
                            modifier = Modifier.size(36.dp)
                        .clickable {
                            showSheet = false
                        }
                        .padding(start = 12.dp)

                )



                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.padding(16.dp)
                        .verticalScroll(scrollState)

                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.testTag(TestTags.ExpandableTextView.bottomSheetContentText)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ExpandableTextViewPreview() {
    ExpandableTextView(text = MockData.longSentence)
}
