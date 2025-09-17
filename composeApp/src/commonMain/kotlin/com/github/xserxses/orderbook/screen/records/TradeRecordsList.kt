package com.github.xserxses.orderbook.screen.records

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@Composable
fun TradeHistoryList(
    records: List<TradeRecordUi>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(records.size) { index ->
            TradeHistoryListItem(records[index])
        }
    }
}

@Composable
private fun TradeHistoryListItem(item: TradeRecordUi) {
    Card {
        ListItem(
            leadingContent = { Text(item.dateTime) },
            headlineContent = {
                Text(
                    text = item.price.toString(),
                )
            },
            supportingContent = {
                Text(
                    text = item.quantity.toString(),
                )
            },
        )
    }
}

@Preview
@Composable
private fun TradeHistoryListPreview_SingleItem() {
    TradeHistoryList(
        records =
            listOf(
                TradeRecordUi(quantity = 10, price = 100.5, dateTime = "10.10.2025 12:00"),
            ),
    )
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
private fun TradeHistoryListPreview_MultipleItems() {
    TradeHistoryList(
        records =
            listOf(
                TradeRecordUi(quantity = 10, price = 100.5, dateTime = "10.10.2025 12:00"),
                TradeRecordUi(quantity = 5, price = 101.2, dateTime = "10.10.2025 12:10"),
                TradeRecordUi(quantity = 20, price = 99.8, dateTime = "10.10.2025 12:270"),
            ),
    )
}
