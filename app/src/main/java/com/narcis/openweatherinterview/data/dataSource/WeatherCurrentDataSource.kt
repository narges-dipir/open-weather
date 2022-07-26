package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.GetNearByWeather
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherCurrentDataSource @Inject constructor(
    private val getNearByWeather: GetNearByWeather) : IWeatherCurrentDataStore {

    override suspend fun getWeatherDataSource(latLng: LocationModel): WeatherResponse  {
        println( " &&&& " + latLng)

        println("it is : " + getNearByWeather.getNearByWeather(50.22, 40.22) )
        return  getNearByWeather.getNearByWeather(latLng.lat, latLng.long) }

}