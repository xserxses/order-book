package com.github.xserxses.orderbook.repository.record

import me.tatarka.inject.annotations.Inject

@Inject
class TradeRecordRepository {
    suspend fun getAll(): List<TradeRecord> = emptyList()
}

data class TradeRecord(
    val price: Double,
    val quantity: Int,
    val timestamp: Long,
)
