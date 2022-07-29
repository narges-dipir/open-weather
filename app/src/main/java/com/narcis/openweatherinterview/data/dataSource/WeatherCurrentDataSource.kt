package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.weather.GetNearByWeather
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherCurrentDataSource @Inject constructor(
    private val getNearByWeather: GetNearByWeather
) : IWeatherCurrentDataStore {
    override suspend fun getWeatherDataSource(latLng: LocationModel): WeatherResponse  {
        return  getNearByWeather.getNearByWeather(latLng.lat.toString(), latLng.long.toString()) }

}