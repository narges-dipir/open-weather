package com.narcis.database.data.weather.db

import com.narcis.database.data.weather.daily.entities.ForecastEntity
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.WeatherItem
import javax.inject.Inject


interface WeatherItemMapper {
    fun mapToDataWeatherItem(weatherEntity: WeatherEntity) : WeatherItem
    fun mapToDatabaseWeather(weatherItem: WeatherItem) : WeatherEntity
    fun maptoDataForecastItem(forecastEntity: ForecastEntity): ForecastItem
    fun mapToDatabaseForecast(forecastItem: ForecastItem): ForecastEntity
}
@ExperimentalStdlibApi
class WeatherItemMapperImp : WeatherItemMapper {
    override fun mapToDataWeatherItem(weatherEntity: WeatherEntity): WeatherItem {
        return WeatherItem(
            weatherEntity.id,
            weatherEntity.main,
            weatherEntity.description,
            weatherEntity.temp,
            weatherEntity.tempMin,
            weatherEntity.tempMax,
            weatherEntity.name,
            weatherEntity.lat,
            weatherEntity.lng
        )
    }

    override fun mapToDatabaseWeather(weatherItem: WeatherItem): WeatherEntity {
        return WeatherEntity(
            weatherItem.id,
            weatherItem.main,
            weatherItem.name,
            weatherItem.description,
            weatherItem.temp,
            weatherItem.temp_min,
            weatherItem.temp_max,
            weatherItem.lat,
            weatherItem.lng)
    }

    override fun maptoDataForecastItem(forecastEntity: ForecastEntity): ForecastItem =
        ForecastItem(
            forecastEntity.dt,
            forecastEntity.id,
            forecastEntity.temp,
            forecastEntity.tempMin,
            forecastEntity.tempMax,
            forecastEntity.description
        )
    override fun mapToDatabaseForecast(forecastItem: ForecastItem): ForecastEntity =
        ForecastEntity(
            forecastItem.dt,
            forecastItem.id,
            forecastItem.temp,
            forecastItem.temp_min,
            forecastItem.temp_max,
            forecastItem.description
        )


}


