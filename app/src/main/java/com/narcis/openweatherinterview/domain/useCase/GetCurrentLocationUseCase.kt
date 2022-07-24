package com.narcis.openweatherinterview.domain.useCase

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.repository.locationRepository.ILocationRepository
import com.narcis.openweatherinterview.di.IoDispatcher
import com.narcis.openweatherinterview.domain.ResultWrapper
import com.narcis.openweatherinterview.utils.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val iLocationRepository: ILocationRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, LocationModel>(ioDispatcher) {

    override fun execute(parameter: Unit): Flow<ResultWrapper<LocationModel>> {
        return iLocationRepository.getCurrentLocation()
    }

}
