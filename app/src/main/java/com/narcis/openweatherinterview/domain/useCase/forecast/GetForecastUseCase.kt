package com.narcis.openweatherinterview.domain.useCase.forecast

import com.narcis.model.di.IoDispatcher
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.LocationModel
import com.narcis.openweatherinterview.data.repository.forecastRepository.IGetForecastRepository
import com.narcis.model.domain.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val getForecastRepository: IGetForecastRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<LocationModel, List<ForecastItem>>(ioDispatcher) {
    override fun execute(parameter: LocationModel): Flow<ResultWrapper<List<ForecastItem>>>
    = getForecastRepository.getForecastRepository(parameter)

}