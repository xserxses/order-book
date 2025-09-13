package com.github.xserxses.orderbook.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.xserxses.orderbook.ui.OrderBookTheme
import orderbook.composeapp.generated.resources.Res
import orderbook.composeapp.generated.resources.general_error
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorComposable(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = stringResource(Res.string.general_error))
    }
}

@Preview
@Composable
private fun ErrorComposablePreview() {
    OrderBookTheme { ErrorComposable() }
}
