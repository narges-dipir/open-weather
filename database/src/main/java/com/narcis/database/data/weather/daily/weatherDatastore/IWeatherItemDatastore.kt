package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.flow.Flow


interface IWeatherItemDatastore {
    suspend fun saveWeatherItem(weatherItem: WeatherItem)
    fun getWeatherItem() : Flow<ResultWrapper<List<WeatherItem>>>
    suspend fun getWeatherItemByName(name: String) : WeatherItem?
}
