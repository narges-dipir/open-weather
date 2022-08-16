package com.narcis.database.data.weather.daily.entities

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = WeatherEntity.WeatherSchema.TABLE_NAME,

)
data class WeatherEntity(

    @ColumnInfo(name = WeatherSchema.ID) val id : Int
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
