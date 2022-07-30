package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.narcis.openweatherinterview.data.dataSource.IForecastDataSource
import com.narcis.openweatherinterview.data.model.ForecastItem
import com.narcis.openweatherinterview.data.model.ForecastResponse
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForecastRepository @Inject constructor(
    private val iForecastDataSource: IForecastDataSource
) : IGetForecastRepository{
    override suspend fun getForecastRepository(latLong: LocationModel): Flow<List<ResultWrapper<ForecastItem>>> {
        return flow {

         val item =  iForecastDataSource.getForecastDataSource(latLong).mapToForecastItem()
        emit(listOf(ResultWrapper.Success(item)))}
    }

    private fun ForecastResponse.mapToForecastItem() : ForecastItem {
        return ForecastItem(
            temp = this.main.temp,
            temp_min = this.main.tempMin,
            temp_max = this.main.tempMax,
            description = this.weather[0].description
        )
    }
}