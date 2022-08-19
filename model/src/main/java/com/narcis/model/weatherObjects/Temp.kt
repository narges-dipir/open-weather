package com.narcis.model.weatherObjects

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("min") val min : Float,
    @SerializedName("max") val max : Float
)
