package com.narcis.openweatherinterview.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.domain.data
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeeklyItem
import com.narcis.openweatherinterview.domain.useCase.GetCurrentLocationUseCase
import com.narcis.openweatherinterview.domain.useCase.forecast.GetWeeklyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject constructor(
    private val getWeeklyUseCase: GetWeeklyUseCase,
    getCurrentLocationUseCase: GetCurrentLocationUseCase
) : ViewModel() {
    private val getWeekly = MutableSharedFlow<LatLng?>()
    private val _errorMessage = Channel<String>(1, BufferOverflow.DROP_LATEST)
    private val _weeklyItem = MutableStateFlow<List<WeeklyItem?>>(listOf<WeeklyItem>())
    val weeklyResultByLocation : StateFlow<List<WeeklyItem?>> = _weeklyItem
    val viewState : StateFlow<ResultWrapper<List<WeeklyItem>>?> =
        getWeekly.flatMapLatest { location ->
            if (location == null || location.latitude == 0.0)
                getCurrentLocationUseCase(Unit)
            else
                flowOf(ResultWrapper.Success(LocationModel(location.latitude, location.longitude)))
        }.onEach {
            if (it is ResultWrapper.Error)
                _errorMessage.trySend(it.exception.message ?: "Error")
        }.flatMapLatest { locationModel ->
            getWeeklyUseCase(LocationModel(locationModel.data?.lat?: 0.0, locationModel.data?.long ?: 0.0))
        }.onEach {
            if (it is ResultWrapper.Error)
                _errorMessage.trySend(it.exception.message ?: "Error")
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultWrapper.Loading)


    val isLoading: StateFlow<Boolean> = viewState.mapLatest {
        it == ResultWrapper.Loading
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

        init {
            viewModelScope.launch {
                viewState.filter {
                    it is ResultWrapper.Success && !it.data.equals(null)
                }.mapLatest { it?.data }. collect{weekly ->
                    println(" the weekly is : " + weekly)
                    _weeklyItem.value = weekly!!
                }
            }
        }

  fun  getWeeklyByLocation() {
        viewModelScope.launch {
            getWeekly.emit(null)
        }
    }
}