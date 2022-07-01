package com.narcis.openweatherinterview.data.model

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("temp") val temp : Double,
    @SerializedName("temp_min") val temp_min : Double,
    @SerializedName("temp_max") val temp_max : Double
)
