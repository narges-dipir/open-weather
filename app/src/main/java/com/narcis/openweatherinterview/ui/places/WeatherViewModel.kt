package com.narcis.openweatherinterview.ui.places

import com.narcis.openweatherinterview.data.dataSource.WeatherDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val remotSource: WeatherDataSource,
) {
}