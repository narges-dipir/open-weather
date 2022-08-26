package com.narcis.database.dao.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.narcis.database.data.weather.daily.entities.WeatherEntity
import com.narcis.database.data.weather.daily.tableDao.WeatherDao
import com.narcis.database.data.weather.db.OpenWeatherDatabase
import com.narcis.database.utile.Weather_Faker
import com.narcis.database.utile.createTestDatabase
import com.narcis.model.domain.ResultWrapper
import com.narcis.model.domain.data
import com.narcis.model.weatherActions.WeatherItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDao {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: OpenWeatherDatabase
    private lateinit var weatherDao: WeatherDao
    private var res = MutableStateFlow<WeatherItem?>(null)
    private lateinit var weatherItem: WeatherItem

    @Before
    fun setUp() {
        database = createTestDatabase()
        weatherDao = database.weatherDao
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun save_and_get() {
      runTest {
        val weatherB = Weather_Faker
        println(" *** " + weatherB.toWeather())
        weatherDao.saveWeatherItem(weatherB)
        weatherItem = weatherDao.getWeatherItemByName(weatherB.name).first().toWeather()

        println(" %%% " + weatherItem)
        Assert.assertEquals(weatherB.toWeather(), weatherItem)
    }
    }



    private fun Flow<WeatherEntity>.toDataWeatherFlow() : Flow<ResultWrapper<WeatherItem>> {
        return this.map { items ->
            ResultWrapper.Success(items.toWeather())

        }
    }

    private fun WeatherEntity.toWeather() : WeatherItem {
        return WeatherItem(
            id = this.id,
            main = this.main,
            description = this.description,
            temp = this.temp,
            temp_min = this.tempMin,
            temp_max = this.tempMax,
            name = this.name

        )
    }


 @After
 fun cleanUp() {
     database.close()
 }

}