package com.narcis.openweatherinterview.data.api.weather

import com.narcis.openweatherinterview.data.api.GetNearByWeather
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherAccessApi @Inject constructor(private val getNearByWeather: GetNearByWeather) {
suspend fun getNearByWeather(lat : LocationModel): WeatherResponse =
    getNearByWeather.getNearByWeather(lat.lat, lat.long)
}