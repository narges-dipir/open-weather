package com.narcis.openweatherinterview.data.repository.locationRepository

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository @Inject constructor( ) : ILocationRepository {
    override fun getCurrentLocation(): Flow<ResultWrapper<LocationModel>> {
       return
    }

}