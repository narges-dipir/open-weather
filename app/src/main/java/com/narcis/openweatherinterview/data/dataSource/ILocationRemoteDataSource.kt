package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.model.LocationModel
import kotlinx.coroutines.flow.Flow


interface ILocationRemoteDataSource {
    fun getCurrentLocation() : Flow<LocationModel>
}