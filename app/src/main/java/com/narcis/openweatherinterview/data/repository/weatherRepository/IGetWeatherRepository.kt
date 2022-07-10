package com.narcis.openweatherinterview.data.repository.weatherRepository

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface IGetWeatherRepository {
    fun getWeatherRepository(latLong : LocationModel) :
            Flow<ResultWrapper<WeatherItem>>
}