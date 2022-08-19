package com.narcis.openweatherinterview.data.model

import android.location.Location
import com.narcis.model.weatherActions.LocationModel

fun Location.toDataModel() : LocationModel {
 return LocationModel(latitude, longitude)
}