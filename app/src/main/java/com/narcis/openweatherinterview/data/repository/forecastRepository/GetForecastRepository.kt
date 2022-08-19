package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.narcis.model.domain.ResultWrapper
import com.narcis.openweatherinterview.data.dataSource.IForecastDataSource
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForecastRepository @Inject constructor(
    private val iForecastDataSource: IForecastDataSource
) : IGetForecastRepository{
    override fun getForecastRepository(latLong: LocationModel):Flow<ResultWrapper<List<ForecastItem>>> {
        return flow {
           val item =  iForecastDataSource.getForecastDataSource(latLong).mapToForecastItem()
        emit(ResultWrapper.Success(item))}
    }

    private fun ForecastList.mapToForecastItem() : List<ForecastItem> {
        return this.list.map { item ->  ForecastItem(
            id = item.weather[0].id,
            temp = item.main.temp,
            temp_min = item.main.tempMin,
            temp_max = item.main.tempMax,
            description = item.weather[0].description
        )
    }
}
}