package com.github.xserxses.orderbook.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.xserxses.orderbook.ui.OrderBookTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorComposable(modifier: Modifier = Modifier) {
}

@Preview
@Composable
private fun ErrorComposablePreview() {
    OrderBookTheme { ErrorComposable() }
}
