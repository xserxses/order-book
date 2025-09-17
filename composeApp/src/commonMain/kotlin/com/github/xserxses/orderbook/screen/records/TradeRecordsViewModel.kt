package com.github.xserxses.orderbook.screen.records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.xserxses.orderbook.repository.record.TradeRecordRepository
import com.github.xserxses.orderbook.ui.model.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import me.tatarka.inject.annotations.Inject

@Inject
class TradeRecordsViewModel(
    private val repository: TradeRecordRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<ScreenState<TradeRecordsUi>> =
        MutableStateFlow(
            ScreenState.Loading(),
        )
    val state: StateFlow<ScreenState<TradeRecordsUi>> = _state

    init {
        repository
            .getAll()
            .map { list ->
                list.map {
                    TradeRecordUi(
                        quantity = it.quantity,
                        price = it.price,
                        dateTime = it.timestamp.toString(),
                    )
                }
            }.onEach {
                _state.value =
                    ScreenState.Ui(
                        TradeRecordsUi(
                            records = it,
                        ),
                    )
            }.catch {
                _state.value = ScreenState.Error()
            }.launchIn(viewModelScope)
    }
}

data class TradeRecordsUi(
    val records: List<TradeRecordUi>,
)

data class TradeRecordUi(
    val quantity: Int,
    val price: Double,
    val dateTime: String,
)
