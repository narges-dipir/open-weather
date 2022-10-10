package com.narcis.database.data.weather.daily.weatherDatastore

import androidx.room.Entity
import com.narcis.database.data.weather.daily.entities.ForecastEntity
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.WeatherItemMapper
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.ForecastItem
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

    override fun getForecastItems(): Flow<ResultWrapper<List<ForecastItem>>> {
        return weatherDao.getWeatherItems().toListDataForecastFlow()
    }

    override suspend fun saveForecastItems(forecastItems: List<ForecastItem>) {
        weatherDao.saveForecastItem(
            withContext(ioDispatcher){
                forecastItems.map {
                    weatherItemMapper.mapToDatabaseForecast(it)
                }
            }
        )
    }

    override fun getWeatherItemByName(name: String): Flow<ResultWrapper<WeatherItem>> {
        return weatherDao.getWeatherItemByName(name).toDataWeatherFlow()
    }

    private fun Flow<List<ForecastEntity>>.toListDataForecastFlow() : Flow<ResultWrapper<List<ForecastItem>>> {
        return this.map { items ->
            ResultWrapper.Success(items.toDataForecast())

        }
    }

    private fun List<ForecastEntity>.toDataForecast() : List<ForecastItem> {
        return this.map {
            weatherItemMapper.maptoDataForecastItem(it)
        }
    }
    private fun Flow<WeatherEntity>.toDataWeatherFlow() : Flow<ResultWrapper<WeatherItem>> {
        return this.map { items ->
            ResultWrapper.Success(items.toWeather())

        }
    }
    private fun WeatherEntity.toWeather() : WeatherItem {
        return WeatherItem(
          id = this.id,
         main = this.main,
         description = this.description,
         temp = this.temp,
         temp_min = this.tempMin,
         temp_max = this.tempMax,
         name = this.name,
        lat = this.lat,
        lng = this.lng)
        }

}


