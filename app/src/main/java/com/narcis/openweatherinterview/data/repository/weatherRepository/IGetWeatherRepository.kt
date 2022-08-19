package com.narcis.openweatherinterview.data.repository.weatherRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.flow.Flow

interface IGetWeatherRepository {
    fun getWeatherRepository(latLong : LocationModel) :
            Flow<ResultWrapper<WeatherItem>>
}