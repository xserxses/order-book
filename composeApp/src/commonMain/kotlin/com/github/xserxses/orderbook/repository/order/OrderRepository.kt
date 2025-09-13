package com.github.xserxses.orderbook.repository.order

class OrderRepository {
    suspend fun add(order: Order) {
    }

    suspend fun getAll(): List<Order> = emptyList()
}

data class Order(
    val id: Int,
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
        val price: Double,
    ) : OrderPriceType()

    object Market : OrderPriceType()
}
