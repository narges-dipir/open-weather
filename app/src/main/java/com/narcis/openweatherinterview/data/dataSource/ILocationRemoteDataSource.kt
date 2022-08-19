package com.narcis.openweatherinterview.data.dataSource

import com.narcis.model.weatherActions.LocationModel
import kotlinx.coroutines.flow.Flow


interface ILocationRemoteDataSource {
    fun getCurrentLocation() : Flow<LocationModel>
}