package com.narcis.openweatherinterview.data.repository.weatherRepository

import com.dropbox.android.external.store4.*
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.domain.weather.SaveWeatherItemUseCase
import com.narcis.model.domain.ResultWrapper
import com.narcis.openweatherinterview.data.dataSource.IWeatherCurrentDataStore
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeatherItem
import com.narcis.model.weatherActions.WeatherResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetWeatherRepository @Inject constructor(
    private val iWeatherCurrentDataStore: IWeatherCurrentDataStore,
    private val weatherDao: WeatherDao,
    private val saveWeatherItem: SaveWeatherItemUseCase)
    : IGetWeatherRepository {
    private val store: Store<LocationModel, WeatherEntity> = StoreBuilder.from(
        fetcher = Fetcher.of {
         iWeatherCurrentDataStore.getWeatherDataSource(it)
        },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = {key:LocationModel ->  weatherDao.getWeatherItemByLocation(key.lat, key.long)},
            writer = { _: LocationModel, input: WeatherResponse ->
                val currentWeather = input.mapWeatherToItem()
                saveWeatherItem(currentWeather)
            }
        )
    ).build()

    override fun getWeatherRepository(latLong: LocationModel):
            Flow<ResultWrapper<WeatherItem>> {
        return flow {
            store.stream(StoreRequest.cached(key = latLong, refresh = false))
                .collect { response: StoreResponse<WeatherEntity> ->
                    when(response) {
                        is StoreResponse.Loading -> {
                            emit(ResultWrapper.Loading)
                        }
                        is StoreResponse.Error -> {
                            val e: Exception = Exception()
                            emit(ResultWrapper.Error(e))
                        }
                        is StoreResponse.Data -> {
                            val data = response.value
                            emit(ResultWrapper.Success(data.mapToDataWeatherItem()))
                        }
                    }

                }

        }
    }

    private fun WeatherResponse.mapWeatherToItem() : WeatherItem {
        return WeatherItem(
            id = this.weather[0].id,
            name = this.name,
            main = this.weather[0].main,
            description = this.weather[0].description,
            temp = this.main.temp,
            temp_min = this.main.tempMin,
            temp_max = this.main.tempMax,
            lat = this.coord.lat,
            lng = this.coord.lon
            )
    }

    private fun WeatherEntity.mapToDataWeatherItem(): WeatherItem {
        return WeatherItem(
            this.id,
            this.main,
            this.description,
            this.temp,
            this.tempMin,
            this.tempMax,
            this.name,
            this.lat,
            this.lng
        )
    }

}