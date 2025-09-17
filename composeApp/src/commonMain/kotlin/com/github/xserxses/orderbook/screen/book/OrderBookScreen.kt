package com.github.xserxses.orderbook.screen.book

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.xserxses.orderbook.ui.composables.ErrorComposable
import com.github.xserxses.orderbook.ui.composables.LoadingComposable
import com.github.xserxses.orderbook.ui.model.ScreenState
import me.tatarka.inject.annotations.Inject

typealias OrderBookScreen = @Composable () -> Unit

@Inject
@Composable
fun OrderBookScreen(orderBookViewModel: () -> OrderBookViewModel) {
    val vm = viewModel { orderBookViewModel() }

    OrderBookScreenContent(
        state = vm.state.value,
    )
}

@Composable
private fun OrderBookScreenContent(
    state: ScreenState<OrderBookUi>,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is ScreenState.Error<OrderBookUi> -> ErrorComposable()
        is ScreenState.Loading<OrderBookUi> -> LoadingComposable()
        is ScreenState.Ui<OrderBookUi> ->
            OrderBookScreenContent(
                state.data,
            )
    }
}
