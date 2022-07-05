package com.narcis.openweatherinterview.data.repository.locationRepository

import com.narcis.openweatherinterview.data.model.LocationModel


interface ILocationRemoteDataStore {
    fun getCurrentLocation() : kotlinx.coroutines.flow.Flow<LocationModel>
}