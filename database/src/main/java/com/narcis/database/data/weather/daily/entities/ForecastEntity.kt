package com.narcis.database.data.weather.daily.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = ForecastEntity.ForecastSchema.TABLE_NAME,
    primaryKeys = [ForecastEntity.ForecastSchema.DT]
)
data class ForecastEntity(
    @ColumnInfo(name = ForecastSchema.DT) val dt : Float,
    @ColumnInfo(name = ForecastSchema.ID) val id : Int,
    @ColumnInfo(name = ForecastSchema.TEMP) val temp : Double,
    @ColumnInfo(name = ForecastSchema.TEMP_MAX) val tempMax : Double,
    @ColumnInfo(name = ForecastSchema.TEMP_MIN) val tempMin : Double,
    @ColumnInfo(name = ForecastSchema.DESCRIPTION) val description: String

) {
    object ForecastSchema {
        const val TABLE_NAME =  "forecast"
        const val DT = "dt"
        const val ID = "id"
        const val TEMP = "temp"
        const val TEMP_MIN = "tempMin"
        const val TEMP_MAX = "tempMax"
        const val DESCRIPTION = "description"

    }
}
