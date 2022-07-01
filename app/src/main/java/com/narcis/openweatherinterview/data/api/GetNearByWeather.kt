package com.narcis.openweatherinterview.data.api

import com.narcis.openweatherinterview.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNearByWeather {

    @GET("weather")
    suspend fun getNearByWeather(
        @Query("lat") lat : Double?,
        @Query("lon") lon : Double?,
        @Query("appid") key: String = "994f5ded78c950a3f394f6cc65f83fb6"
    ) : WeatherResponse
}