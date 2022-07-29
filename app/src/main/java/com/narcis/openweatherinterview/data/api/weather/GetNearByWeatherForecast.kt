package com.narcis.openweatherinterview.data.api.weather

import com.narcis.openweatherinterview.data.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GetNearByWeatherForecast {

    @GET("forecast")
    suspend fun getNearByForecast(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("appid") appid: String = "994f5ded78c950a3f394f6cc65f83fb6",
    ) : ForecastResponse
}