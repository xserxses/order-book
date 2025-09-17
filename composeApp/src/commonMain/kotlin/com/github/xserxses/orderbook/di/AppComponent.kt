package com.github.xserxses.orderbook.di

import androidx.room.RoomDatabase
import com.github.xserxses.orderbook.persistance.AppDatabase
import com.github.xserxses.orderbook.persistance.AppDatabaseConstructor
import com.github.xserxses.orderbook.persistance.dao.OrderDao
import com.github.xserxses.orderbook.persistance.getRoomDatabase
import com.github.xserxses.orderbook.repository.order.OrderRepository
import com.github.xserxses.orderbook.repository.record.TradeRecordRepository
import com.github.xserxses.orderbook.screen.book.OrderBookScreen
import com.github.xserxses.orderbook.screen.neworder.NewOrderScreen
import com.github.xserxses.orderbook.screen.records.TradeHistoryScreen
import com.github.xserxses.orderbook.utils.DateTimeProvider
import com.github.xserxses.orderbook.utils.DefaultDateTimeProvider
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class Singleton

@Component
@Singleton
abstract class AppComponent(
    @get:Provides val dbBuilder: RoomDatabase.Builder<AppDatabase>,
) {
    @Provides
    @Singleton
    fun provideTradeRecordRepository() = TradeRecordRepository()

    @Provides
    @Singleton
    fun provideOrderRepository(orderDao: OrderDao) = OrderRepository(orderDao)

    @Provides
    fun provideDateTimeProvider(): DateTimeProvider = DefaultDateTimeProvider()

    @Provides
    @Singleton
    fun provideDatabase() =
        getRoomDatabase(dbBuilder).also {
            AppDatabaseConstructor.initialize()
        }

    @Provides
    fun provideOrderDao(database: AppDatabase) = database.getOrderDao()

    @Provides
    fun provideTradeRecordDao(database: AppDatabase) = database.getTradeRecordDao()

    abstract val orderBookScreen: OrderBookScreen

    abstract val newOrderScreen: NewOrderScreen
    abstract val tradeHistoryScreen: TradeHistoryScreen
}

@KmpComponentCreate
expect fun createKmp(dbBuilder: RoomDatabase.Builder<AppDatabase>): AppComponent
