package com.narcis.openweatherinterview.ui.places

import com.narcis.openweatherinterview.data.repository.weatherRepository.GetWeatherRepository
import com.narcis.openweatherinterview.domain.useCase.GetCurrentLocationUseCase
import com.narcis.openweatherinterview.domain.useCase.weather.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    getCurrentLocationUseCase: GetCurrentLocationUseCase
) {
}