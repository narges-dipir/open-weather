package com.narcis.openweatherinterview.di

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.narcis.openweatherinterview.data.api.weather.GetNearByWeather
import com.narcis.openweatherinterview.data.dataSource.*
import com.narcis.openweatherinterview.data.repository.forecastRepository.GetForecastRepository
import com.narcis.openweatherinterview.data.repository.forecastRepository.GetWeeklyForecastRepository
import com.narcis.openweatherinterview.data.repository.forecastRepository.IGetForecastRepository
import com.narcis.openweatherinterview.data.repository.forecastRepository.IGetWeeklyForecastRepository
import com.narcis.openweatherinterview.data.repository.locationRepository.ILocationRepository
import com.narcis.openweatherinterview.data.repository.locationRepository.LocationRepository
import com.narcis.openweatherinterview.data.repository.weatherRepository.GetWeatherRepository
import com.narcis.openweatherinterview.data.repository.weatherRepository.IGetWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WeatherApiProvider {

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("LOG")
                    .response("LOG")
                    .build()
            )
        }.build()
    }
    @Provides
    @Singleton
    fun provideRetrofitConnection(
        okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit) : GetNearByWeather {
        return retrofit.create(GetNearByWeather::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideForecastApi(retrofit: Retrofit) : GetNearByWeatherForecast {
//        return retrofit.create(GetNearByWeatherForecast::class.java )
//    }

    @Provides
    fun provideGetWeatherRepository(weatherCurrentDataSource: WeatherCurrentDataSource):
    IGetWeatherRepository = GetWeatherRepository(weatherCurrentDataSource)

    @Provides
    fun provideGetForecastRepository(forecastDataSource: ForecastDataSource)
    : IGetForecastRepository = GetForecastRepository(forecastDataSource)

    @Provides
    fun provideGetWeeklyRepository(weeklyDataSource: WeeklyDataSource) :
            IGetWeeklyForecastRepository = GetWeeklyForecastRepository(weeklyDataSource)

}


@Module
@InstallIn(SingletonComponent::class)
abstract class LocationSourceModule {
    @Binds
    internal abstract fun bindsLocationSource(
        locationSource: LocationRemoteDataSource
    ): ILocationRemoteDataSource

    @Binds
    internal abstract fun bindsLocationRepository(
        repository: LocationRepository
    ): ILocationRepository
}