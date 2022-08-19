package com.narcis.database.data.weather.db

import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.model.weatherActions.WeatherItem
import javax.inject.Inject


internal interface WeatherItemMapper {
    fun mapToDataWeatherItem(weatherEntity: WeatherEntity) : WeatherItem
    fun mapToDatabaseWeather(weatherItem: WeatherItem) : WeatherEntity
}
@ExperimentalStdlibApi
internal class WeatherItemMapperImp : WeatherItemMapper {
    override fun mapToDataWeatherItem(weatherEntity: WeatherEntity): WeatherItem {
        return WeatherItem(
            weatherEntity.id,
            weatherEntity.main,
            weatherEntity.description,
            weatherEntity.temp,
            weatherEntity.tempMin,
            weatherEntity.tempMax,
            weatherEntity.name
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
            weatherItem.temp_max)
    }


}


