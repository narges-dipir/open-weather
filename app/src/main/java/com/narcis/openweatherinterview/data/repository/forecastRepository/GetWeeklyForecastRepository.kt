package com.narcis.openweatherinterview.data.repository.forecastRepository


import com.narcis.openweatherinterview.data.dataSource.IWeeklyDataSource
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeeklyItem
import com.narcis.openweatherinterview.data.model.weatherObjects.WeeklyList
import com.narcis.openweatherinterview.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeeklyForecastRepository @Inject constructor(
    private val weeklyDataSource: IWeeklyDataSource
) : IGetWeeklyForecastRepository {
    override fun getWeeklyForecastRepository(latLng: LocationModel): Flow<ResultWrapper<List<WeeklyItem>>> {
        return flow {
          val item = weeklyDataSource.getWeeklyDataSource(latLng).mapToweeklyItem()
           emit(ResultWrapper.Success(item))
        }
    }


   fun WeeklyList.mapToweeklyItem() : List<WeeklyItem> {
       return this.weeklyList.map { wekly ->
           WeeklyItem(
               min = wekly.temp.min,
               max = wekly.temp.min,
               description = wekly.weather[0].description
           )
       }
   }

}