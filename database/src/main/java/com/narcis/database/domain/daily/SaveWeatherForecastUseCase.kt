package com.narcis.database.domain.daily

import com.narcis.database.data.weather.daily.weatherDatastore.IWeatherItemDatastore
import com.narcis.database.domain.SuspendUseCase
import com.narcis.model.di.IoDispatcher
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveWeatherForecastUseCase @Inject constructor(
    private val iWeatherItemDatastore: IWeatherItemDatastore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<List<ForecastItem>, Unit>(ioDispatcher){
    override suspend fun execute(parameters: List<ForecastItem>) =
        iWeatherItemDatastore.saveForecastItems(parameters)
}