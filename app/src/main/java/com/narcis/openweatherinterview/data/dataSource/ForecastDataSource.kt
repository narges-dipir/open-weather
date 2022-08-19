package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.weather.GetNearByWeather
import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.LocationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastDataSource @Inject constructor(
    private val getNearbyForecast : GetNearByWeather
) : IForecastDataSource{
    override suspend fun getForecastDataSource(latLng: LocationModel): ForecastList =
        getNearbyForecast.getNearByForecast(latLng.lat.toString(), latLng.long.toString())

}