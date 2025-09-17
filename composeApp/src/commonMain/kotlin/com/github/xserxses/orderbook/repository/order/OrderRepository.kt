package com.github.xserxses.orderbook.repository.order

import com.github.xserxses.orderbook.persistance.dao.OrderDao
import com.github.xserxses.orderbook.persistance.dao.OrderEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRepository(
    private val orderDao: OrderDao,
) {
    suspend fun add(order: Order) {
        orderDao.insert(
            OrderEntity(
                id = order.id,
                quantity = order.quantity,
                price = (order.priceType as OrderPriceType.Limit).price,
                timestamp = order.timestamp,
            ),
        )
    }

    fun getAll(): Flow<List<Order>> =
        orderDao
            .getAllAsFlow()
            .map {
                it.map {
                    Order(
                        id = it.id,
                        quantity = it.quantity,
                        priceType = OrderPriceType.Limit(price = it.price),
                        type = OrderType.BUY,
                        timestamp = it.timestamp,
                    )
                }
            }
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
