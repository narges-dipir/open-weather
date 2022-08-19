package com.narcis.model.weatherObjects

import com.google.gson.annotations.SerializedName

data class WeeklyList (
    @SerializedName("daily") val weeklyList: List<WeeklyResponse>
        )


data class WeeklyResponse(
    @SerializedName("temp") val temp : Temp ,
    @SerializedName("weather") val weather: List<Weather>,
)
