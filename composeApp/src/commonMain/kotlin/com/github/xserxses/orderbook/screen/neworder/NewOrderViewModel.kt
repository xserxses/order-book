package com.github.xserxses.orderbook.screen.neworder

import androidx.lifecycle.ViewModel
import com.github.xserxses.orderbook.repository.order.OrderRepository

class NewOrderViewModel(
    private val orderRepository: OrderRepository,
) : ViewModel() {
    fun placeLimitOrder(
        quantity: Int,
        price: Float,
        type: OrderTypeUi,
    ) {
    }

    fun placeMarketOrder(
        quantity: Int,
        type: OrderTypeUi,
    ) {
    }
}

enum class OrderTypeUi {
    BUY,
    SELL,
}

enum class PricingTypeUi {
    LIMIT,
    MARKET,
}
