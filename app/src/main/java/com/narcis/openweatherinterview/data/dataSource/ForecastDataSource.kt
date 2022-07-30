package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.weather.GetNearByWeatherForecast
import com.narcis.openweatherinterview.data.model.ForecastResponse
import com.narcis.openweatherinterview.data.model.LocationModel
import javax.inject.Inject

class ForecastDataSource @Inject constructor(
    private val getNearbyForecast : GetNearByWeatherForecast
) : IForecastDataSource{
    override suspend fun getForecastDataSource(latLng: LocationModel): ForecastResponse {
        println(" wea ra here")
    return getNearbyForecast.getNearByForecast(latLng.lat.toString(), latLng.long.toString())
    }
}