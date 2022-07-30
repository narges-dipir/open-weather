package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.narcis.openweatherinterview.data.model.ForecastItem
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface IGetForecastRepository {
    suspend fun getForecastRepository(latLong : LocationModel) : Flow<List<ResultWrapper<ForecastItem>>>
}