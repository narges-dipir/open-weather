package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.weather.GetNearByWeather
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.weatherObjects.WeeklyList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeeklyDataSource @Inject constructor(
    private val getNearByWeather: GetNearByWeather
)  : IWeeklyDataSource{
    override suspend fun getWeeklyDataSource(latLng: LocationModel): WeeklyList {
        return getNearByWeather.getWeeklyForecast(latLng.lat.toString(), latLng.long.toString())
    }
}