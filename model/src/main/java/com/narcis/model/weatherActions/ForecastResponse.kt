package com.narcis.model.weatherActions

import com.google.gson.annotations.SerializedName
import com.narcis.model.weatherObjects.Clouds
import com.narcis.model.weatherObjects.Main
import com.narcis.model.weatherObjects.Weather
import com.narcis.model.weatherObjects.Wind

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
