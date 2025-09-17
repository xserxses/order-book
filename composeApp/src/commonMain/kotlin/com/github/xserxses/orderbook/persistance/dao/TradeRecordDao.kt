package com.github.xserxses.orderbook.persistance.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey

@Dao
interface TradeRecordDao {
    @Insert
    suspend fun insert(order: TradeRecordEntity)

//    @Query("SELECT * FROM TradeRecordEntity")
//    fun getAllAsFlow(): Flow<List<TradeRecordEntity>>
}

@Entity
data class TradeRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long,
)
