package com.narcis.database.domain.weather

import com.narcis.database.data.weather.daily.weatherDatastore.IWeatherItemDatastore
import com.narcis.model.di.IoDispatcher
import com.narcis.model.domain.FlowUseCase
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllWeatherItemsUseCase @Inject constructor(
    private val iWeatherItemDatastore: IWeatherItemDatastore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit,List<ForecastItem>>(ioDispatcher) {
    override fun execute(parameter: Unit): Flow<ResultWrapper<List<ForecastItem>>> {
        return iWeatherItemDatastore.getForecastItems()
    }
}