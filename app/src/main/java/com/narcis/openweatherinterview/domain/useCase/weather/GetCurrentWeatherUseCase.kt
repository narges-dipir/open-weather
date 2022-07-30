package com.narcis.openweatherinterview.domain.useCase.weather

import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.data.repository.weatherRepository.GetWeatherRepository
import com.narcis.openweatherinterview.data.repository.weatherRepository.IGetWeatherRepository
import com.narcis.openweatherinterview.di.IoDispatcher
import com.narcis.openweatherinterview.domain.ResultWrapper
import com.narcis.openweatherinterview.utils.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val getWeatherRepository: IGetWeatherRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
    ) : FlowUseCase<LocationModel, WeatherItem> (ioDispatcher){
    override fun execute(parameter: LocationModel): Flow<ResultWrapper<WeatherItem>> =
        getWeatherRepository.getWeatherRepository(parameter)
}