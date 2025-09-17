package com.github.xserxses.orderbook.persistance.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Insert
    suspend fun insert(order: OrderEntity)

    @Query("SELECT * FROM OrderEntity")
    fun getAllAsFlow(): Flow<List<OrderEntity>>
}

@Entity
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quantity: Int,
    val price: Float,
    val timestamp: Long,
)
