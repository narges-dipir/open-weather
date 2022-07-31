package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.weatherObjects.WeeklyList

interface IWeeklyDataSource {
    suspend fun getWeeklyDataSource(latLng : LocationModel) : WeeklyList
}