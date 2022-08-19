package com.narcis.openweatherinterview.data.dataSource

import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherResponse

interface IWeatherCurrentDataStore {
    suspend fun getWeatherDataSource(latLng : LocationModel)
    : WeatherResponse
}