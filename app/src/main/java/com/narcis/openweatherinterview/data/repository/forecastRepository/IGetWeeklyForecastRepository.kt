package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeeklyItem
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface IGetWeeklyForecastRepository {
    fun getWeeklyForecastRepository(latLng : LocationModel) : Flow<ResultWrapper<List<WeeklyItem>>>
}