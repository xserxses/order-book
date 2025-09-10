package com.github.xserxses.orderbook.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.xserxses.orderbook.navigation.OrderBook
import orderbook.composeapp.generated.resources.Res
import orderbook.composeapp.generated.resources.add_cd
import orderbook.composeapp.generated.resources.ic_add
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppFloatingActionButton(
    currentRoute: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = currentRoute == OrderBook.route,
        enter = scaleIn(),
        exit = scaleOut(),
    ) {
        FloatingActionButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            Icon(
                vectorResource(
                    Res.drawable.ic_add,
                ),
                stringResource(Res.string.add_cd),
            )
        }
    }
}

@Composable
@Preview
private fun AppFloatingActionButtonPreview() {
    OrderBookTheme {
        AppFloatingActionButton(
            currentRoute = OrderBook.route,
            onClick = {},
        )
    }
}
