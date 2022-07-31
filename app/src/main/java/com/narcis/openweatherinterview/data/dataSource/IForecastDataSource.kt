package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.model.ForecastList
import com.narcis.openweatherinterview.data.model.LocationModel

interface IForecastDataSource {
    suspend fun getForecastDataSource(latLng : LocationModel) : ForecastList
}
