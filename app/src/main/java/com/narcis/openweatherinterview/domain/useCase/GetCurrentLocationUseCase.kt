package com.narcis.openweatherinterview.domain.useCase

import com.narcis.model.di.IoDispatcher
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.openweatherinterview.data.repository.locationRepository.ILocationRepository
import com.narcis.model.domain.FlowUseCase
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
