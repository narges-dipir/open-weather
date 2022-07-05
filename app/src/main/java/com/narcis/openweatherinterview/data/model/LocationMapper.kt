package com.narcis.openweatherinterview.data.model

import android.location.Location

fun Location.toDataModel() : LocationModel {
 return LocationModel(latitude, longitude)
}