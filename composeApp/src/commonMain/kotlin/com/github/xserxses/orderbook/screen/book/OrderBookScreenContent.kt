package com.github.xserxses.orderbook.screen.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import orderbook.composeapp.generated.resources.Res
import orderbook.composeapp.generated.resources.asks_label
import orderbook.composeapp.generated.resources.best_ask_label
import orderbook.composeapp.generated.resources.best_bid_label
import orderbook.composeapp.generated.resources.bids_label
import orderbook.composeapp.generated.resources.spread_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun OrderBookScreenContent(
    state: OrderBookUi,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(16.dp)
                .fillMaxSize(),
    ) {
        Card {
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(text = state.assets)
                    Spacer(modifier = Modifier.weight(1f))
                    Column {
                        Text(text = stringResource(Res.string.spread_label))
                        Text(
                            modifier = Modifier.align(Alignment.End),
                            text = state.spread.toString(),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Column {
                        Text(text = stringResource(Res.string.best_bid_label))
                        Text(text = state.bestBid)
                    }
                    Spacer(Modifier.weight(0.05f))
                    Column {
                        Text(text = stringResource(Res.string.best_ask_label))
                        Text(
                            text = state.bestAsk,
                            modifier = Modifier.align(Alignment.End),
                        )
                    }
                }
            }
        }
        Row(Modifier.padding(vertical = 8.dp)) {
            Text(text = stringResource(Res.string.bids_label))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = stringResource(Res.string.asks_label))
        }
        Box(
            modifier =
                Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer),
        )
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            LazyColumn(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(8.dp),
            ) {
                items(state.buys.size) { index ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                    ) {
                        Text(text = state.buys[index].quantity.toString())
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = state.buys[index].price.toString())
                    }
                }
            }
            Box(
                modifier =
                    Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
            )
            LazyColumn(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(8.dp),
            ) {
                items(state.sells.size) { index ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                    ) {
                        Text(text = state.sells[index].price.toString())
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = state.sells[index].quantity.toString())
                    }
                }
            }
        }
    }
}
