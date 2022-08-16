package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import javax.inject.Inject

internal class WeatherItemDatastore @Inject constructor(
    private val weatherDao: WeatherDao,

){
}