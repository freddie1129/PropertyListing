package com.demobnb.propertylisting.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.Highlight
import com.demobnb.propertylisting.model.HighlightConstants
import com.demobnb.propertylisting.model.Host

@Composable
fun HighlightsView(highlights: List<Int>) {

    val highlightsResList = highlights.mapNotNull {
        HighlightConstants.highlightsRepo[it]
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp) ) {
        for (highlight in highlightsResList) {
            val index =
            Row {
                Image(
                    painter = painterResource(id = highlight.iconResId),
                    contentDescription = stringResource(id = highlight.titleResId),
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colorScheme.onSurfaceVariant // Example: good for secondary icons
                    )
                    )
                Spacer(modifier = Modifier.size(16.dp))
                Column {
                    Text(text = stringResource(id = highlight.titleResId),
                        style = MaterialTheme.typography.titleSmall)
                    Text(text = stringResource(id = highlight.descriptionResId),
                        style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }

}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun HighlightsViewPreview() {
    HighlightsView(highlights = listOf(1, 2, 3, 4, 5))
}