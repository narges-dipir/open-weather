package com.narcis.openweatherinterview.data.dataSource

import com.narcis.openweatherinterview.data.api.weather.WeatherAccessApi
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.data.model.WeatherItem
import com.narcis.openweatherinterview.data.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataSource @Inject constructor(private val weatherAccessApi: WeatherAccessApi) {
    private var weatherList : List<WeatherItem> = listOf()
    suspend fun getWeathers(latLng : LocationModel?) : List<WeatherItem> = withContext(Dispatchers.IO) {
        var cachedWeathers = weatherList
        val command = "ping -c 1 google.com"
        val result = kotlin.runCatching { Runtime.getRuntime().exec(command).waitFor() == 0 }
        if (result.isSuccess && latLng != null) {
            cachedWeathers = weatherAccessApi.getNearByWeather(latLng).mapWeatherToItems()

            if (cachedWeathers.size == 0)
                println(" cachedWeathers is empty ! ")

        this@WeatherDataSource.weatherList = cachedWeathers
        }



        return@withContext cachedWeathers
    }

    private fun WeatherResponse.mapWeatherToItems() : List<WeatherItem> {
        return this.mapWeatherToItems().map {
            WeatherItem(
                name = this.name,
                main = this.main,
                weather = this.weather
            )
        }
    }

//    @Throws(InterruptedException::class, IOException::class)
//    fun isConnected(): Boolean {
//        val command = "ping -c 1 google.com"
//        val result = kotlin.runCatching { Runtime.getRuntime().exec(command).waitFor() == 0 }
//        return result.isSuccess
//    }

}