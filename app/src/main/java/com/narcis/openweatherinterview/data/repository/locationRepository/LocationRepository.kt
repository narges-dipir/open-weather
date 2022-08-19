package com.narcis.openweatherinterview.data.repository.locationRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.openweatherinterview.data.dataSource.ILocationRemoteDataSource
import com.narcis.model.weatherActions.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val iLocationRemoteDataStore: ILocationRemoteDataSource
) : ILocationRepository {

    override fun getCurrentLocation(): Flow<ResultWrapper<LocationModel>> {
        return iLocationRemoteDataStore.getCurrentLocation().map {
            ResultWrapper.Success(it)
        }
    }

}