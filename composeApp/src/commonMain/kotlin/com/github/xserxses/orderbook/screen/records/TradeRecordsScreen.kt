package com.github.xserxses.orderbook.screen.records

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.xserxses.orderbook.di.AppComponent

@Composable
fun TradeHistoryScreen(
    appComponent: AppComponent,
    modifier: Modifier = Modifier,
    viewModel: TradeRecordsViewModel =
        viewModel {
            TradeRecordsViewModel(
                appComponent.provideTradeRecordRepository(),
            )
        },
) {
    Text(text = "TradeRecords screen")
}
