package com.narcis.database.data.weather.daily.entities

import android.content.ClipDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.narcis.openweatherinterview.data.model.weatherObjects.Main


@Entity(
    tableName = WeatherEntity.WeatherSchema.TABLE_NAME,
    primaryKeys = [WeatherEntity.WeatherSchema.NAME]
)
data class WeatherEntity(

    @ColumnInfo(name = WeatherSchema.ID) val id : Int,
    @ColumnInfo(name = WeatherSchema.MAIN) val main: String,
    @ColumnInfo(name = WeatherSchema.NAME) val name : String,
    @ColumnInfo(name = WeatherSchema.DESCRIPTION) val description: String,
    @ColumnInfo(name = WeatherSchema.TEMP) val temp : Double,
    @ColumnInfo(name = WeatherSchema.TEMP_MAX) val tempMax : Double,
    @ColumnInfo(name = WeatherSchema.TEMP_MIN) val tempMin : Double,

) {
    object WeatherSchema {
        const val TABLE_NAME =  "weather"
        const val ID = "id"
        const val MAIN = "main"
        const val DESCRIPTION = "description"
        const val TEMP = "temp"
        const val TEMP_MIN = "tempMin"
        const val TEMP_MAX = "tempMax"
        const val NAME = "name"

    }
}
