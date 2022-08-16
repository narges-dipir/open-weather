package com.narcis.database.data.weather.daily.tableDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.narcis.database.data.weather.daily.entities.WeatherEntity

@Dao
internal interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherItem(weatherEntity: WeatherEntity)
}