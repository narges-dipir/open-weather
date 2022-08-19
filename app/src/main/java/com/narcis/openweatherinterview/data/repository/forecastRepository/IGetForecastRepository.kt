package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.LocationModel
import kotlinx.coroutines.flow.Flow

interface IGetForecastRepository {
    fun getForecastRepository(latLong : LocationModel) :Flow<ResultWrapper<List<ForecastItem>>>
}