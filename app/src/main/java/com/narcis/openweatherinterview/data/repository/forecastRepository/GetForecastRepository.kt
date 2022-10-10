package com.narcis.openweatherinterview.data.repository.forecastRepository

import com.dropbox.android.external.store4.*
import com.narcis.database.data.weather.daily.entities.ForecastEntity
import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.WeatherItemMapper
import com.narcis.database.domain.daily.SaveWeatherForecastUseCase
import com.narcis.database.domain.weather.GetAllWeatherItemsUseCase
import com.narcis.model.domain.ResultWrapper
import com.narcis.openweatherinterview.data.dataSource.IForecastDataSource
import com.narcis.model.weatherActions.ForecastItem
import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForecastRepository @Inject constructor(
    private val iForecastDataSource: IForecastDataSource,
    private val weatherDao: WeatherDao,
    private val saveWeatherForecastUseCase: SaveWeatherForecastUseCase,
) : IGetForecastRepository {

    override fun getForecastRepository(latLong: LocationModel): Flow<ResultWrapper<List<ForecastItem>>> {
        return flow {
            StoreBuilder.from(
                fetcher = Fetcher.of {
                    iForecastDataSource.getForecastDataSource(it)
                },
                sourceOfTruth = SourceOfTruth.Companion.of(
                    reader = { weatherDao.getWeatherItems() },
                    writer = { _: LocationModel, input: ForecastList ->
                        val forecast = input.mapToForecastItem()
                        saveWeatherForecastUseCase(forecast)
                    }
                )
            ).build().stream(StoreRequest.cached(key = latLong, refresh = true))
                .collect { response: StoreResponse<List<ForecastEntity>> ->
                    when (response) {
                        is StoreResponse.Loading -> {
                            emit(ResultWrapper.Loading)
                        }
                        is StoreResponse.Error -> {
                            val e: Exception = Exception()
                            emit(ResultWrapper.Error(e))
                        }
                        is StoreResponse.Data -> {
                            emit(ResultWrapper.Success(response.value.toForecastItemList()))
                        }
                    }
                }
        }
    }

    private fun ForecastList.mapToForecastItem(): List<ForecastItem> {
        return this.list.map { item ->
            ForecastItem(
                dt = item.dt,
                id = item.weather[0].id,
                temp = item.main.temp,
                temp_min = item.main.tempMin,
                temp_max = item.main.tempMax,
                description = item.weather[0].description
            )
        }
    }
    private fun List<ForecastEntity>.toForecastItemList() : List<ForecastItem>{
        return this.map { item ->
            ForecastItem(
                dt = item.dt,
                id = item.id,
                temp = item.temp,
                temp_min = item.tempMin,
                temp_max = item.tempMax,
                description = item.description
            )

        }
    }
}