package com.narcis.openweatherinterview.data.repository.locationRepository

import com.narcis.openweatherinterview.data.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface ILocationDataSource {
    fun getCurrentLocation() : Flow<LocationModel>
}