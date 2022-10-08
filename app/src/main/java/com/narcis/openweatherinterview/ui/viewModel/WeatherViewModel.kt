package com.narcis.openweatherinterview.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.narcis.database.domain.weather.GetAllWeatherItemsUseCase
import com.narcis.database.domain.weather.GetWeatherByNameUseCase
import com.narcis.database.domain.weather.SaveWeatherItemUseCase
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.domain.data

import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherItem

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
    private val getAllWeatherItemsUseCase: GetAllWeatherItemsUseCase,
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase
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
            getCurrentWeatherUseCase(
                LocationModel(locationModel.data?.lat ?: 0.0,
                locationModel.data?.long ?: 0.0)
            )
        }.onEach {
            if (it is ResultWrapper.Error){
                _errorMessage.trySend(it.exception.message ?: "Error")
            }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ResultWrapper.Loading)


val isLoading: StateFlow<Boolean> = viewState.mapLatest {
    it == ResultWrapper.Loading
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private val _getAllState = MutableStateFlow<List<WeatherItem>?>(null)
    val getAllState : StateFlow<List<WeatherItem>?> = _getAllState
    private val _getByNameState = MutableStateFlow<WeatherItem?>(null)
    val getByNameState : StateFlow<WeatherItem?> = _getByNameState



    init {
        getAllWeathers()
    viewModelScope.launch {

    viewState.filter {
        it is ResultWrapper.Success && !it.data.equals(null) }
        .mapLatest { it?.data }
        .collect{ weather ->
            weather.let {
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



    private fun getAllWeathers()  {
        viewModelScope.launch {
            getAllWeatherItemsUseCase(Unit).collect{
                if (it.data.isNullOrEmpty()) {
                    _getAllState.value = null
                } else
                    _getAllState.value = it.data
            }
        }

    }

    private fun getWeatherByName(name: String) {
        viewModelScope.launch {
            getWeatherByNameUseCase(name).collect{
                if(it.data == null) {
                    _getByNameState.value = null
                } else
                    _getByNameState.value = it.data
            }
        }
    }

}