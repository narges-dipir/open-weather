package com.narcis.openweatherinterview.domain.useCase.forecast

import com.narcis.model.di.IoDispatcher
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeeklyItem
import com.narcis.openweatherinterview.data.repository.forecastRepository.IGetWeeklyForecastRepository
import com.narcis.model.domain.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeeklyUseCase @Inject constructor(
    private val forecastRepository: IGetWeeklyForecastRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<LocationModel, List<WeeklyItem>>(ioDispatcher){
    override fun execute(parameter: LocationModel): Flow<ResultWrapper<List<WeeklyItem>>> {
        return forecastRepository.getWeeklyForecastRepository(parameter)
    }
}