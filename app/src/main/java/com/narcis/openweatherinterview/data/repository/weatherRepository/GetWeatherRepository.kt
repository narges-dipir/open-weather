package com.narcis.openweatherinterview.data.repository.weatherRepository

import com.narcis.openweatherinterview.data.dataSource.IWeatherCurrentDataStore
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.data.model.WeatherResponse
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetWeatherRepository @Inject constructor(
    private val iWeatherCurrentDataStore: IWeatherCurrentDataStore)
    : IGetWeatherRepository {
    override fun getWeatherRepository(latLong: LocationModel):
            Flow<ResultWrapper<WeatherItem>> {
        return flow {
            val item = iWeatherCurrentDataStore.getWeatherDataSource(latLong).mapWeatherToItems()

            emit(ResultWrapper.Success(item))
        }
    }

    private fun WeatherResponse.mapWeatherToItems() : WeatherItem {
        return WeatherItem(
                name = this.name,
                main = this.main,
                weather = this.weather
            )
    }


}