package com.narcis.database.data.weather.daily.weatherDatastore

import com.narcis.openweatherinterview.data.model.WeatherItem

interface IWeatherItemDatastore {
    suspend fun saveWeatherItem(weatherItem: WeatherItem)
}