package com.narcis.openweatherinterview.data.dataSource

import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.LocationModel

interface IForecastDataSource {
    suspend fun getForecastDataSource(latLng : LocationModel) : ForecastList
}
