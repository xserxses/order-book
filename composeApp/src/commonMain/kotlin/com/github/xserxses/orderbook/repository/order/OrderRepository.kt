package com.github.xserxses.orderbook.repository.order

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OrderRepository {
    private val orders =
        mutableListOf(
            Order(
                id = 1,
                quantity = 2,
                priceType = OrderPriceType.Limit(price = 2.0f),
                type = OrderType.BUY,
                timestamp = 132456789L,
            ),
            Order(
                id = 2,
                quantity = 33,
                priceType = OrderPriceType.Market,
                type = OrderType.BUY,
                timestamp = 123256789L,
            ),
            Order(
                id = 5,
                quantity = 23,
                priceType = OrderPriceType.Market,
                type = OrderType.SELL,
                timestamp = 123456739L,
            ),
            Order(
                id = 5,
                quantity = 23,
                priceType = OrderPriceType.Limit(price = 3.5f),
                type = OrderType.SELL,
                timestamp = 123456739L,
            ),
        )

    suspend fun add(order: Order) {
        orders.add(order)
    }

    fun getAll(): Flow<List<Order>> = flowOf(orders)
}

data class Order(
    val id: Long = 0,
    val quantity: Int,
    val priceType: OrderPriceType,
    val type: OrderType,
    val timestamp: Long,
)

enum class OrderType {
    BUY,
    SELL,
}

sealed class OrderPriceType {
    data class Limit(
        val price: Float,
    ) : OrderPriceType()

    object Market : OrderPriceType()
}
