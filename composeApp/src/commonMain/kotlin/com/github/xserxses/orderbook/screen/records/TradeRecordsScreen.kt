package com.github.xserxses.orderbook.screen.records

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.xserxses.orderbook.ui.OrderBookTheme
import com.github.xserxses.orderbook.ui.composables.ErrorComposable
import com.github.xserxses.orderbook.ui.composables.LoadingComposable
import com.github.xserxses.orderbook.ui.model.ScreenState
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import org.jetbrains.compose.ui.tooling.preview.Preview

typealias TradeHistoryScreen = @Composable (modifier: Modifier) -> Unit

@Composable
@Inject
fun TradeHistoryScreen(
    @Assisted modifier: Modifier = Modifier,
    viewModel: () -> TradeRecordsViewModel,
) {
    val vm = viewModel { viewModel() }

    TradeHistoryScreenContent(
        state = vm.state.value,
        modifier = modifier,
    )
}

@Composable
private fun TradeHistoryScreenContent(
    state: ScreenState<TradeRecordsUi>,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is ScreenState.Error<TradeRecordsUi> -> ErrorComposable()
        is ScreenState.Loading<TradeRecordsUi> -> LoadingComposable()
        is ScreenState.Ui<TradeRecordsUi> ->
            TradeHistoryList(
                records = state.data.records,
                modifier = modifier,
            )
    }
}

@Preview
@Composable
private fun TradeHistoryScreenContentLoadingPreview() {
    OrderBookTheme {
        TradeHistoryScreenContent(ScreenState.Loading())
    }
}

@Preview
@Composable
private fun TradeHistoryScreenContentErrorPreview() {
    OrderBookTheme {
        TradeHistoryScreenContent(ScreenState.Error())
    }
}
