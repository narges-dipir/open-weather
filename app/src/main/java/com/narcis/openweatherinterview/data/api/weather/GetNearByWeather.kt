package com.narcis.openweatherinterview.data.api.weather

import com.narcis.model.weatherActions.ForecastList
import com.narcis.model.weatherActions.WeatherResponse
import com.narcis.model.weatherObjects.WeeklyList
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNearByWeather {

    @GET("weather")
    suspend fun getNearByWeather(
        @Query("lat") lat : String,
        @Query("lon") lon : String?,
        @Query("appid") appid: String = "994f5ded78c950a3f394f6cc65f83fb6",
        @Query("units") units : String = "metric"
    ) : WeatherResponse

    @GET("forecast")
    suspend fun getNearByForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("appid") appid: String = "994f5ded78c950a3f394f6cc65f83fb6",
        @Query("units") units : String = "metric"
    ) : ForecastList


    @GET("onecall")
    suspend fun getWeeklyForecast(
        @Query("lat") lat : String,
        @Query("lon") lon: String,
        @Query("exclude") exclude : String = "current%2Cminutely%2Chourly",
        @Query("appid") appid: String = "994f5ded78c950a3f394f6cc65f83fb6",
        @Query("units") units : String = "metric"
    ) : WeeklyList

}