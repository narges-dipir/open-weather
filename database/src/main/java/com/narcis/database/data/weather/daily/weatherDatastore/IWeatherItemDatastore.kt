package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.flow.Flow


interface IWeatherItemDatastore {
    suspend fun saveWeatherItem(weatherItem: WeatherItem)
    fun getForecastItems() : Flow<ResultWrapper<List<ForecastItem>>>
    suspend fun saveForecastItems(forecastItems: List<ForecastItem>)
    fun getWeatherItemByName(name: String) : Flow<ResultWrapper<WeatherItem>>
}
