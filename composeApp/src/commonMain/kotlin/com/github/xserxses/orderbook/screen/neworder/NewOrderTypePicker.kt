package com.github.xserxses.orderbook.screen.neworder

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.xserxses.orderbook.ui.OrderBookTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun <T> NewOrderTypePicker(
    initialValue: T,
    options: List<T>,
    labels: List<String>,
    onOptionSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedOption: T by remember { mutableStateOf(initialValue) }

    SingleChoiceSegmentedButtonRow(
        modifier =
            modifier
                .fillMaxWidth(),
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = selectedOption == option,
                onClick = {
                    selectedOption = option
                    onOptionSelected(option)
                },
                shape = MaterialTheme.shapes.medium,
            ) {
                Text(text = labels[options.indexOf(option)])
            }
            if (index != options.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
@Preview
private fun NewOrderTypePickerPreview() {
    OrderBookTheme {
        NewOrderTypePicker(
            initialValue = "Buy",
            options = listOf("Buy", "Sell"),
            labels = listOf("Buy", "Sell"),
            onOptionSelected = {},
        )
    }
}

@Composable
@Preview
private fun NewOrderTypePickerPreviewWithDifferentTypes() {
    OrderBookTheme {
        NewOrderTypePicker(
            initialValue = 1,
            options = listOf(1, 2, 3),
            labels = listOf("Option 1", "Option 2", "Option 3"),
            onOptionSelected = { },
        )
    }
}
