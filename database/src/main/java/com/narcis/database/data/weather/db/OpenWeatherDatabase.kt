package com.narcis.database.data.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.database.data.weather.daily.tableDao.WeatherDao


@Database(
    entities = [
        WeatherEntity::class
    ],
    version = 2
)
internal abstract class OpenWeatherDatabase : RoomDatabase() {
    abstract val weatherDao : WeatherDao

}