package com.github.xserxses.orderbook.screen.neworder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.xserxses.orderbook.ui.OrderBookTheme
import com.github.xserxses.orderbook.ui.model.OrderTypeUi
import com.github.xserxses.orderbook.ui.model.PricingTypeUi
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import orderbook.composeapp.generated.resources.Res
import orderbook.composeapp.generated.resources.buy_label
import orderbook.composeapp.generated.resources.limit_label
import orderbook.composeapp.generated.resources.market_label
import orderbook.composeapp.generated.resources.new_order_title
import orderbook.composeapp.generated.resources.place_order_button
import orderbook.composeapp.generated.resources.sell_label
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

typealias NewOrderScreen = @Composable (onDismiss: () -> Unit, modifier: Modifier) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Inject
@Composable
fun NewOrderScreen(
    @Assisted onDismiss: () -> Unit,
    @Assisted modifier: Modifier = Modifier,
    viewModel: () -> NewOrderViewModel,
) {
    val vm = viewModel { viewModel() }

    NewOrderScreenContent(
        onDismiss = onDismiss,
        modifier = modifier,
        onLimitOrder = { quantity, price, orderType ->
            vm.placeLimitOrder(quantity, price, orderType)
        },
        onMarketOrder = { quantity, orderType ->
            vm.placeMarketOrder(quantity, orderType)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewOrderScreenContent(
    onLimitOrder: (Int, Float, OrderTypeUi) -> Unit,
    onMarketOrder: (Int, OrderTypeUi) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        var orderType: OrderTypeUi by remember { mutableStateOf(OrderTypeUi.BUY) }
        var priceType: PricingTypeUi by remember { mutableStateOf(PricingTypeUi.LIMIT) }
        var quantity: Int? by remember { mutableStateOf(INITIAL_QUANTITY) }
        var price: Float? by remember { mutableStateOf(INITIAL_PRICE) }

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.new_order_title),
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(8.dp))

            NewOrderTypePicker(
                initialValue = orderType,
                options = listOf(OrderTypeUi.BUY, OrderTypeUi.SELL),
                labels =
                    listOf(
                        stringResource(Res.string.buy_label),
                        stringResource(Res.string.sell_label),
                    ),
                onOptionSelect = { type ->
                    orderType = type
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            NewOrderTypePicker(
                initialValue = priceType,
                options = listOf(PricingTypeUi.LIMIT, PricingTypeUi.MARKET),
                labels =
                    listOf(
                        stringResource(Res.string.limit_label),
                        stringResource(Res.string.market_label),
                    ),
                onOptionSelect = { type ->
                    priceType = type
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            NewOrderQuantityPicker(
                onValueChange = { new ->
                    quantity = new
                },
                modifier =
                    Modifier
                        .fillMaxWidth(),
                valueValidator = { it > 0 },
                initialValue = INITIAL_QUANTITY,
            )

            Spacer(modifier = Modifier.height(8.dp))

            AnimatedVisibility(
                visible = priceType == PricingTypeUi.LIMIT,
                enter =
                    expandVertically(
                        expandFrom = Alignment.CenterVertically,
                    ),
                exit =
                    shrinkVertically(
                        shrinkTowards = Alignment.CenterVertically,
                    ),
            ) {
                Column {
                    NewOrderPricePicker(
                        onValueChange = { new ->
                            price = new
                        },
                        modifier =
                            Modifier
                                .fillMaxWidth(),
                        valueValidator = { it > 0 },
                        initialValue = INITIAL_PRICE,
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Button(
                enabled =
                    quantity != null &&
                        (
                            priceType == PricingTypeUi.MARKET ||
                                (priceType == PricingTypeUi.LIMIT && price != null)
                        ),
                modifier =
                    Modifier
                        .fillMaxWidth(),
                onClick = {
                    when (priceType) {
                        PricingTypeUi.LIMIT -> {
                            onLimitOrder(quantity!!, price!!, orderType)
                        }

                        PricingTypeUi.MARKET -> {
                            onMarketOrder(quantity!!, orderType)
                        }
                    }
                    onDismiss()
                },
            ) {
                Text(text = stringResource(Res.string.place_order_button))
            }
        }
    }
}

private const val INITIAL_QUANTITY = 1
private const val INITIAL_PRICE = 1f

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NewOrderScreenPreview() {
    OrderBookTheme {
        NewOrderScreenContent(
            onLimitOrder = { _, _, _ -> },
            onMarketOrder = { _, _ -> },
            onDismiss = {},
            sheetState =
                rememberStandardBottomSheetState(
                    initialValue = SheetValue.Expanded,
                ),
        )
    }
}
