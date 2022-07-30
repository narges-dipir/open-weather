package com.narcis.openweatherinterview.domain.useCase.forecast

import com.narcis.openweatherinterview.data.model.ForecastItem
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.repository.forecastRepository.GetForecastRepository
import com.narcis.openweatherinterview.data.repository.forecastRepository.IGetForecastRepository
import com.narcis.openweatherinterview.di.IoDispatcher
import com.narcis.openweatherinterview.domain.ResultWrapper
import com.narcis.openweatherinterview.utils.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val getForecastRepository: IGetForecastRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :FlowUseCase<LocationModel, List<ForecastItem>> (ioDispatcher) {
    override fun execute(parameter: LocationModel): Flow<ResultWrapper<List<ForecastItem>>> {
        println(" the loc for forecast is : " + parameter)
        return flow {  getForecastRepository.getForecastRepository(parameter) }
    }
}