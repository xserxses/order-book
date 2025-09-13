package com.github.xserxses.orderbook.repository.record

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.tatarka.inject.annotations.Inject

@Inject
class TradeRecordRepository {
    private val records =
        mutableListOf(
            TradeRecord(price = 2.0, quantity = 1, timestamp = 123746789L),
            TradeRecord(price = 3.0, quantity = 2, timestamp = 123453789L),
            TradeRecord(price = 6.0, quantity = 3, timestamp = 123452389L),
            TradeRecord(price = 9.0, quantity = 6, timestamp = 123457989L),
        )

    suspend fun add(record: TradeRecord) {
        records.add(record)
    }

    fun getAll(): Flow<List<TradeRecord>> = flowOf(records)
}

data class TradeRecord(
    val id: Long = 0,
    val price: Double,
    val quantity: Int,
    val timestamp: Long,
)
