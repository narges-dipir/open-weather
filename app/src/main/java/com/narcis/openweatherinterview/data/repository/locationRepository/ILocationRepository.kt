package com.narcis.openweatherinterview.data.repository.locationRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import kotlinx.coroutines.flow.Flow

interface ILocationRepository {
    fun getCurrentLocation(): Flow<ResultWrapper<LocationModel>>
}