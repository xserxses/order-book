package com.github.xserxses.orderbook.screen.neworder

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewOrderScreen(
    modifier: Modifier = Modifier,
    viewModel: NewOrderViewModel = viewModel { NewOrderViewModel() },
) {
    Text(text = "NewOrder dialog")
}
