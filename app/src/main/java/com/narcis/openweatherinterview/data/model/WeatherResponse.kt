package com.narcis.openweatherinterview.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather : Weather,
    @SerializedName("main") val main : Temperature,
    @SerializedName("name") val name : String
)