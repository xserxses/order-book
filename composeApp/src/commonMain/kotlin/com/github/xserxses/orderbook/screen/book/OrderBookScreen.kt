package com.github.xserxses.orderbook.screen.book

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OrderBookScreen(
    onNewOrder: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(text = "Order Book screen")
        Button(onClick = onNewOrder) {
            Text("Go to NewOrder")
        }
    }
}
