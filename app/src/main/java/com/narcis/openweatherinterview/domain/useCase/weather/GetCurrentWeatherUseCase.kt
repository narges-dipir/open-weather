package com.narcis.openweatherinterview.domain.useCase.weather

import com.narcis.model.di.IoDispatcher
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherItem
import com.narcis.openweatherinterview.data.repository.weatherRepository.IGetWeatherRepository
import com.narcis.model.domain.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val getWeatherRepository: IGetWeatherRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
    ) : FlowUseCase<LocationModel, WeatherItem>(ioDispatcher){
    override fun execute(parameter: LocationModel): Flow<ResultWrapper<WeatherItem>> =
        getWeatherRepository.getWeatherRepository(parameter)
}