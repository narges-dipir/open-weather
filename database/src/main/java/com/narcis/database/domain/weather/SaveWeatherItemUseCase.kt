package com.narcis.database.domain.weather

import com.narcis.database.data.weather.daily.weatherDatastore.IWeatherItemDatastore
import com.narcis.database.domain.SuspendUseCase
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveWeatherItemUseCase @Inject constructor(
    private val iWeatherItemDatastore: IWeatherItemDatastore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<WeatherItem,Unit>(ioDispatcher){
    override suspend fun execute(parameters: WeatherItem) =
        iWeatherItemDatastore.saveWeatherItem(parameters)
}