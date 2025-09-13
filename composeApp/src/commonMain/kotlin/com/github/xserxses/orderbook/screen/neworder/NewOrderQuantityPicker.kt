package com.github.xserxses.orderbook.screen.neworder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import orderbook.composeapp.generated.resources.ic_add
import orderbook.composeapp.generated.resources.ic_remove
import orderbook.composeapp.generated.resources.quantity_label
import orderbook.composeapp.generated.resources.quantity_picker_minus_cd
import orderbook.composeapp.generated.resources.quantity_picker_plus_cd
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NewOrderQuantityPicker(
    onValueChange: (Int?) -> Unit,
    modifier: Modifier = Modifier,
    valueValidator: (Int) -> Boolean = { true },
    initialValue: Int = 1,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var text by remember { mutableStateOf(initialValue.toString()) }
        var isError by remember { mutableStateOf(false) }
        var additionEnabled by remember { mutableStateOf(valueValidator(initialValue + 1)) }
        var substractionEnabled by remember { mutableStateOf(valueValidator(initialValue - 1)) }

        IconButton(
            enabled = substractionEnabled,
            onClick = {
                val number = text.toIntOrNull()
                isError = number == null
                number?.let {
                    if (valueValidator(number - 1)) {
                        val newValue = it - 1
                        text = newValue.toString()
                        onValueChange.invoke(newValue)
                        substractionEnabled = valueValidator(newValue - 1)
                        additionEnabled = true
                    }
                }
            },
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_remove),
                contentDescription = stringResource(Res.string.quantity_picker_minus_cd),
            )
        }
        OutlinedTextField(
            label = {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(Res.string.quantity_label),
                )
            },
            value = text,
            onValueChange = { newText ->
                text = newText
                val number = newText.toIntOrNull()
                isError = number == null || !valueValidator(number)
                onValueChange.invoke(number.takeIf { !isError })
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
            modifier =
                Modifier
                    .weight(0.5f),
        )
        IconButton(
            enabled = additionEnabled,
            onClick = {
                val number = text.toIntOrNull()
                isError = number == null
                number?.let {
                    if (valueValidator(number + 1)) {
                        val newValue = it + 1
                        text = newValue.toString()
                        onValueChange.invoke(newValue.takeIf { !isError })
                        additionEnabled = valueValidator(newValue + 1)
                        substractionEnabled = true
                    }
                }
            },
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_add),
                contentDescription = stringResource(Res.string.quantity_picker_plus_cd),
            )
        }
    }
}

@Composable
@Preview
private fun NumberPickerComposablePreview() {
    OrderBookTheme {
        NewOrderQuantityPicker(
            onValueChange = {},
        )
    }
}
