package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeeklyItem
import kotlinx.coroutines.flow.Flow

interface IGetWeeklyForecastRepository {
    fun getWeeklyForecastRepository(latLng : LocationModel) : Flow<ResultWrapper<List<WeeklyItem>>>
}