package com.github.xserxses.orderbook.screen.records

import androidx.lifecycle.ViewModel
import com.github.xserxses.orderbook.repository.record.TradeRecordRepository

class TradeRecordsViewModel(
    private val repository: TradeRecordRepository,
) : ViewModel()
