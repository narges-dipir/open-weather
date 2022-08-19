package com.narcis.openweatherinterview.data.repository.forecastRepository


import com.narcis.model.domain.ResultWrapper
import com.narcis.openweatherinterview.data.dataSource.IWeeklyDataSource
import com.narcis.model.weatherActions.LocationModel
import com.narcis.model.weatherActions.WeeklyItem
import com.narcis.model.weatherObjects.WeeklyList
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
              id = wekly.weather[0].id,
               min = wekly.temp.min,
               max = wekly.temp.min,
               description = wekly.weather[0].description
           )
       }
   }

}