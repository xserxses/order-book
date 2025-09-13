package com.github.xserxses.orderbook.screen.book

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun OrderBookScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderBookViewModel = viewModel { OrderBookViewModel() },
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = "Order Book screen")
    }
}
