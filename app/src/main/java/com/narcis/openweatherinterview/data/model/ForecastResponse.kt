package com.narcis.openweatherinterview.data.model

import com.google.gson.annotations.SerializedName
import com.narcis.openweatherinterview.data.model.weatherObjects.Clouds
import com.narcis.openweatherinterview.data.model.weatherObjects.Main
import com.narcis.openweatherinterview.data.model.weatherObjects.Weather
import com.narcis.openweatherinterview.data.model.weatherObjects.Wind
import java.util.concurrent.Flow

data class ForecastList (
    @SerializedName("list") val list : List<ForecastResponse>
        )
data class ForecastResponse(
    @SerializedName("dt") val dt : Float,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("pop") val pop : Float,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("dt_txt") val date : String

)
