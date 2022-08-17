package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.WeatherItemMapper
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class WeatherItemDatastore @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherItemMapper: WeatherItemMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IWeatherItemDatastore{
    override suspend fun saveWeatherItem(weatherItem: WeatherItem) {
       weatherDao.saveWeatherItem(
           withContext(ioDispatcher) {
               weatherItemMapper.mapToDatabaseWeather(weatherItem)
           }
       )
    }
}