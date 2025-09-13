package com.github.xserxses.orderbook.screen.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.xserxses.orderbook.ui.OrderBookTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OrderBookScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderBookViewModel = viewModel { OrderBookViewModel() },
) {
    OrderBookScreenContent(modifier)
}

@Composable
private fun OrderBookScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier =
            modifier
                .padding(16.dp)
                .fillMaxSize(),
    ) {
        Card {
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(text = "PLN/USD")
                    Spacer(modifier = Modifier.weight(1f))
                    Column {
                        Text(text = "Spread")
                        Text(
                            modifier = Modifier.align(Alignment.End),
                            text = "0.3",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Column {
                        Text(text = "Best Bid")
                        Text(text = "29.500")
                    }
                    Spacer(Modifier.weight(0.05f))
                    Column {
                        Text(text = "Best Ask")
                        Text(
                            text = "29.200",
                            modifier = Modifier.align(Alignment.End),
                        )
                    }
                }
            }
        }
        Row(modifier.padding(vertical = 8.dp)) {
            Text(text = "Bids")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Asks")
        }
        Box(
            modifier =
                Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondaryContainer),
        )
        Row {
            LazyColumn { }
            LazyColumn { }
        }
    }
}

@Preview
@Composable
private fun OrderBookScreenPreview() {
    OrderBookTheme {
        OrderBookScreenContent()
    }
}
