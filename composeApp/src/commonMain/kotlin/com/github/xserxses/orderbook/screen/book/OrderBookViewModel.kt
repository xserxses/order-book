package com.github.xserxses.orderbook.screen.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.xserxses.orderbook.logging.createLogger
import com.github.xserxses.orderbook.repository.order.Order
import com.github.xserxses.orderbook.repository.order.OrderPriceType
import com.github.xserxses.orderbook.repository.order.OrderRepository
import com.github.xserxses.orderbook.repository.order.OrderType
import com.github.xserxses.orderbook.ui.model.PricingTypeUi
import com.github.xserxses.orderbook.ui.model.ScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import me.tatarka.inject.annotations.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@Inject
class OrderBookViewModel(
    orderRepository: OrderRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<ScreenState<OrderBookUi>> =
        MutableStateFlow(
            ScreenState.Loading(),
        )
    val state: StateFlow<ScreenState<OrderBookUi>> = _state

    init {
        orderRepository
            .getAll()
            .mapLatest { orders ->
                logger.d { "Mapping orders to UI $orders" }

                val mapped =
                    OrderBookUi(
                        assets = "BTC",
                        spread = "0.5",
                        bestAsk = "X",
                        bestBid = "X",
                        buys =
                            orders
                                .filter { it.type == OrderType.BUY }
                                .map { mapOrderToUi(it) },
                        sells =
                            orders
                                .filter { it.type == OrderType.SELL }
                                .map { mapOrderToUi(it) },
                    )
                logger.d { "mapped $mapped" }
                mapped
            }.onEach {
                _state.value = ScreenState.Ui(it)
                logger.d { "Emitting $it" }
            }.launchIn(viewModelScope)
    }

    private fun mapOrderToUi(order: Order): OrderBookRecordUi {
        val price = (order.priceType as? OrderPriceType.Limit)?.price
        return OrderBookRecordUi(
            price = price,
            quantity = order.quantity,
            type =
                if (price != null) {
                    PricingTypeUi.LIMIT
                } else {
                    PricingTypeUi.MARKET
                },
        )
    }

    companion object {
        val logger = createLogger().withTag("OrderBookViewModel")
    }
}

data class OrderBookUi(
    val assets: String,
    val spread: String,
    val bestBid: String,
    val bestAsk: String,
    val buys: List<OrderBookRecordUi>,
    val sells: List<OrderBookRecordUi>,
)

data class OrderBookRecordUi(
    val price: Float?,
    val quantity: Int,
    val type: PricingTypeUi,
)
