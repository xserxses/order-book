package com.github.xserxses.orderbook.repository.order

class OrderRepository {
    private val orders =
        mutableListOf(
            Order(
                id = 1,
                quantity = 2,
                priceType = OrderPriceType.Limit(price = 2.0),
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
                priceType = OrderPriceType.Limit(price = 3.5),
                type = OrderType.SELL,
                timestamp = 123456739L,
            ),
        )

    suspend fun add(order: Order) {
        orders.add(order)
    }

    suspend fun getAll(): List<Order> = orders
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
        val price: Double,
    ) : OrderPriceType()

    object Market : OrderPriceType()
}
