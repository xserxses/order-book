package com.github.xserxses.orderbook.screen.neworder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.xserxses.orderbook.repository.order.Order
import com.github.xserxses.orderbook.repository.order.OrderPriceType
import com.github.xserxses.orderbook.repository.order.OrderRepository
import com.github.xserxses.orderbook.repository.order.OrderType
import com.github.xserxses.orderbook.ui.model.OrderTypeUi
import com.github.xserxses.orderbook.utils.DateTimeProvider
import kotlinx.coroutines.launch

class NewOrderViewModel(
    private val orderRepository: OrderRepository,
    private val dateTimeProvider: DateTimeProvider,
) : ViewModel() {
    fun placeLimitOrder(
        quantity: Int,
        price: Float,
        type: OrderTypeUi,
    ) {
        viewModelScope.launch {
            orderRepository
                .add(
                    Order(
                        quantity = quantity,
                        priceType = OrderPriceType.Limit(price = price),
                        type =
                            when (type) {
                                OrderTypeUi.BUY -> OrderType.BUY
                                OrderTypeUi.SELL -> OrderType.SELL
                            },
                        timestamp = dateTimeProvider.currentTimeMillis(),
                    ),
                )
        }
    }

    fun placeMarketOrder(
        quantity: Int,
        type: OrderTypeUi,
    ) {
        viewModelScope.launch {
            orderRepository
                .add(
                    Order(
                        quantity = quantity,
                        priceType = OrderPriceType.Market,
                        type =
                            when (type) {
                                OrderTypeUi.BUY -> OrderType.BUY
                                OrderTypeUi.SELL -> OrderType.SELL
                            },
                        timestamp = dateTimeProvider.currentTimeMillis(),
                    ),
                )
        }
    }
}
