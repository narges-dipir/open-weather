package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.WeatherItemMapper
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getWeatherItem(): Flow<ResultWrapper<List<WeatherItem>>> {
       return weatherDao.getWeatherItems().toListDataWeatherFlow()
    }

    override suspend fun getWeatherItemByName(name: String): WeatherItem? {
        val weather =  weatherDao.getWeatherItemByName(name)
        return if (weather != null)
         weather
        else
            return null
    }


    private fun Flow<List<WeatherEntity>>.toListDataWeatherFlow() : Flow<ResultWrapper<List<WeatherItem>>> {
        return this.map { items ->
        ResultWrapper.Success(items.toDataWeather())

        }
    }

private suspend fun List<WeatherEntity>.toDataWeather() : List<WeatherItem> {
    return this.map {
        weatherItemMapper.mapToDataWeatherItem(it)
    }
}




}


