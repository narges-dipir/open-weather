package com.narcis.database.data.weather.daily.tableDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.flow.Flow

@Dao
internal interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherItem(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM ${WeatherEntity.WeatherSchema.TABLE_NAME}")
    fun getWeatherItems() : Flow<List<WeatherEntity>>

    @Query("SELECT * FROM ${WeatherEntity.WeatherSchema.TABLE_NAME} where " +
            "${WeatherEntity.WeatherSchema.NAME} = :name")
    suspend fun getWeatherItemByName(name: String) : WeatherItem
}