package com.narcis.openweatherinterview.ui.places.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.domain.ResultWrapper
import com.narcis.openweatherinterview.domain.data
import com.narcis.openweatherinterview.domain.useCase.GetCurrentLocationUseCase
import com.narcis.openweatherinterview.domain.useCase.weather.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    getCurrentLocationUseCase: GetCurrentLocationUseCase,
): ViewModel() {
    private val getWeather = MutableSharedFlow<LatLng?>()
    private val _errorMessage = Channel<String>(1, BufferOverflow.DROP_LATEST)
    private val _weatherItem = MutableStateFlow<WeatherItem?>(null)
    val weatherResultsByLocation : StateFlow<WeatherItem?> = _weatherItem
    val errorMessage :Flow<String> = _errorMessage.receiveAsFlow().
    shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000))

    private val viewState : StateFlow<ResultWrapper<WeatherItem>?> =
        getWeather.flatMapLatest { location ->
            if (location == null || location.latitude == 0.0) {
                getCurrentLocationUseCase(Unit)
            } else {
                flowOf(ResultWrapper.Success(LocationModel(location.latitude, location.longitude)))
            }
        }.onEach {
            if (it is ResultWrapper.Error) {
                _errorMessage.trySend(it.exception.message ?: "Error")
            }
        }.flatMapLatest { locationModel ->
            getCurrentWeatherUseCase(LocationModel(locationModel.data?.lat ?: 0.0,
                locationModel.data?.long ?: 0.0))
        }.onEach {
            if (it is ResultWrapper.Error){
                _errorMessage.trySend(it.exception.message ?: "Error")
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultWrapper.Loading)


val isLoading: StateFlow<Boolean> = viewState.mapLatest {
    it == ResultWrapper.Loading
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)



    init {

    viewModelScope.launch {

    viewState.filter {
        it is ResultWrapper.Success && !it.data.equals(null) }
        .mapLatest { it?.data }
        .collect{ weather ->
            weather.let {
                println(" the weather is : " + weather)
                _weatherItem.value = weather
            }

        }
     }

        viewModelScope.launch {  }

    }

    fun getWeatherByLat() {
        viewModelScope.launch {
            getWeather.emit(null)
        }
    }

}