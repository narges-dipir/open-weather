package com.narcis.openweatherinterview.data.repository.weatherRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.openweatherinterview.data.dataSource.IWeatherCurrentDataStore
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherItem
import com.narcis.model.weatherActions.WeatherResponse

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
            id = this.weather[0].id,
            name = this.name,
            main = this.weather[0].main,
            description = this.weather[0].description,
            temp = this.main.temp,
            temp_min = this.main.tempMin,
            temp_max = this.main.tempMax
            )
    }


}