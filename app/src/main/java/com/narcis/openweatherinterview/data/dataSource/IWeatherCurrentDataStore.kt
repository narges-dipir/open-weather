package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherResponse

interface IWeatherCurrentDataStore {

    suspend fun getWeatherDataSource(
        latLng : LocationModel)
    : WeatherResponse
}