package com.github.xserxses.orderbook.persistance

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.github.xserxses.orderbook.persistance.dao.OrderDao
import com.github.xserxses.orderbook.persistance.dao.OrderEntity
import com.github.xserxses.orderbook.persistance.dao.TradeRecordDao
import com.github.xserxses.orderbook.persistance.dao.TradeRecordEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [OrderEntity::class, TradeRecordEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getOrderDao(): OrderDao

    abstract fun getTradeRecordDao(): TradeRecordDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase =
    builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
