package com.narcis.database.data.weather.db

import com.narcis.openweatherinterview.data.model.WeatherItem


internal interface WeatherItemMapper {
    fun mapToDataWeatherItem() : WeatherItem
}


