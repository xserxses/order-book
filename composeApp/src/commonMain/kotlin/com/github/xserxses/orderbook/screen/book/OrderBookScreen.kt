package com.github.xserxses.orderbook.screen.book

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.xserxses.orderbook.di.AppComponent
import com.github.xserxses.orderbook.ui.composables.ErrorComposable
import com.github.xserxses.orderbook.ui.composables.LoadingComposable
import com.github.xserxses.orderbook.ui.model.ScreenState

@Composable
fun OrderBookScreen(
    appComponent: AppComponent,
    modifier: Modifier = Modifier,
    viewModel: OrderBookViewModel =
        viewModel {
            OrderBookViewModel(
                orderRepository = appComponent.provideOrderRepository(),
            )
        },
) {
    OrderBookScreenContent(
        state = viewModel.state.value,
        modifier,
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
