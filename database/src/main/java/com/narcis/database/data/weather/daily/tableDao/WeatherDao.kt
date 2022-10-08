package com.narcis.database.data.weather.daily.tableDao

import androidx.room.*
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherItem(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM ${WeatherEntity.WeatherSchema.TABLE_NAME}")
    fun getWeatherItems() : Flow<List<WeatherEntity>>

    @Query("SELECT * FROM ${WeatherEntity.WeatherSchema.TABLE_NAME} where " +
            "${WeatherEntity.WeatherSchema.NAME} = :name")
     fun getWeatherItemByName(name: String) : Flow<WeatherEntity>

    @Query("SELECT * FROM ${WeatherEntity.WeatherSchema.TABLE_NAME} where " +
            "${WeatherEntity.WeatherSchema.LAT}= :lat AND ${WeatherEntity.WeatherSchema.LONG} = :lng")
    fun getWeatherItemByLocation(lat: Double, lng: Double) : Flow<WeatherEntity>
}