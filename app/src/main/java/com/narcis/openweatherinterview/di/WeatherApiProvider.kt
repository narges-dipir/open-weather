package com.narcis.openweatherinterview.di

import com.narcis.openweatherinterview.data.api.GetNearByWeather
import com.narcis.openweatherinterview.data.dataSource.ILocationRemoteDataSource
import com.narcis.openweatherinterview.data.dataSource.IWeatherCurrentDataStore
import com.narcis.openweatherinterview.data.dataSource.LocationRemoteDataSource
import com.narcis.openweatherinterview.data.dataSource.WeatherCurrentDataSource
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WeatherApiProvider {

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient {
        return OkHttpClient.Builder().build()
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
//    fun provideWeatherCurrentDataStore(api : GetNearByWeather) : IWeatherCurrentDataStore =
//        WeatherCurrentDataSource(api)


    @Provides
    fun provideGetWeatherRepository(weatherCurrentDataSource: WeatherCurrentDataSource):
    IGetWeatherRepository = GetWeatherRepository(weatherCurrentDataSource)
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