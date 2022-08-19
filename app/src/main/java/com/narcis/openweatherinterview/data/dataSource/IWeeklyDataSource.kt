package com.narcis.openweatherinterview.data.dataSource

import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherObjects.WeeklyList

interface IWeeklyDataSource {
    suspend fun getWeeklyDataSource(latLng : LocationModel) : WeeklyList
}