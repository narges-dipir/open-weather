package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.WeatherItemMapper
import com.narcis.openweatherinterview.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

internal class WeatherItemDatastore @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherItemMapper: WeatherItemMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
){
}