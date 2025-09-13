package com.demobnb.propertylisting.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demobnb.propertylisting.R
import com.demobnb.propertylisting.extension.CurrencyProvider
import com.demobnb.propertylisting.extension.format
import com.demobnb.propertylisting.extension.formatStayRange
import java.time.LocalDate


@Composable
fun ReserveView(
    price: Float, checkInDate: LocalDate, checkOutDate: LocalDate,
    modifier: Modifier,
    onClick : () -> Unit) {



    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .drawBehind {
                // Draw a line at the top
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 2f
                )
            }
            .padding(vertical = 8.dp, horizontal = 10.dp)) {
        Column {

            Text(text ="${CurrencyProvider.symbol}${price.format()} ${CurrencyProvider.code} ${stringResource(R.string.total)}",
                 style = MaterialTheme.typography.titleMedium.copy(textDecoration = TextDecoration.Underline)
            )
            Text(formatStayRange(checkInDate, checkOutDate),
                style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color.Red,
                contentColor = androidx.compose.ui.graphics.Color.White
            )) {
            Text(stringResource(R.string.reserve))
        }
    }

}

@Preview
@Composable
fun ReserveViewPreview() {
    ReserveView(price = 150f, checkInDate = LocalDate.now(),
        checkOutDate = LocalDate.now().plusDays(133),
        modifier = Modifier,
        onClick = {})
}