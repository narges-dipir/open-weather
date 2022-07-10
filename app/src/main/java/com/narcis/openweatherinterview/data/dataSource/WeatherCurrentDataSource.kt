package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.GetNearByWeather
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherResponse
import javax.inject.Inject


class WeatherCurrentDataSource @Inject constructor(
    private val getNearByWeather: GetNearByWeather) : IWeatherCurrentDataStore {

    override suspend fun getWeatherDataSource(latLng: LocationModel): WeatherResponse =
        getNearByWeather.getNearByWeather(latLng.lat, latLng.long)

}