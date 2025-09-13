package com.github.xserxses.orderbook.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.xserxses.orderbook.ui.OrderBookTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingComposable(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingComposablePreview() {
    OrderBookTheme { LoadingComposable() }
}
