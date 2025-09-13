package com.github.xserxses.orderbook.screen.neworder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.github.xserxses.orderbook.ui.OrderBookTheme
import orderbook.composeapp.generated.resources.Res
import orderbook.composeapp.generated.resources.price_label
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NewOrderPricePicker(
    onValueChange: (Float?) -> Unit,
    modifier: Modifier = Modifier,
    valueValidator: (Float) -> Boolean = { true },
    initialValue: Float = 1.0f,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var text by remember { mutableStateOf(initialValue.toString()) }
        var isError by remember { mutableStateOf(false) }

        OutlinedTextField(
            label = {
                Text(
                    text = stringResource(Res.string.price_label),
                )
            },
            value = text,
            onValueChange = { newText ->
                text = newText
                val number = newText.toFloatOrNull()
                isError = number == null || !valueValidator(number)
                if (!isError) {
                    onValueChange.invoke(number)
                } else {
                    onValueChange.invoke(null)
                }
            },
            singleLine = true,
            textStyle =
                TextStyle(
                    color =
                        if (isError) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                ),
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
@Preview
private fun NumberPickerComposablePreview() {
    OrderBookTheme {
        NewOrderPricePicker(
            onValueChange = {},
        )
    }
}
