package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.model.ForecastResponse
import com.narcis.openweatherinterview.data.model.LocationModel

interface IForecastDataSource {
    suspend fun getForecastDataSource(latLng : LocationModel) : ForecastResponse
}