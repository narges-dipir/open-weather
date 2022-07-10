package com.narcis.openweatherinterview.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.narcis.openweatherinterview.data.dataSource.ILocationRemoteDataSource
import com.narcis.openweatherinterview.data.dataSource.LocationRemoteDataSource
import com.narcis.openweatherinterview.data.repository.locationRepository.ILocationRepository
import com.narcis.openweatherinterview.data.repository.locationRepository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocationModule {

    @Provides
    internal fun providesFusedLocationProviderClient(
        @ApplicationContext context: Context) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context.applicationContext)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationBindModule{
    @Binds
    abstract fun bindLocationDataSource (locationRemoteDataSource: LocationRemoteDataSource) : ILocationRemoteDataSource

    @Binds
    abstract fun bindLocationRepository(locationRepository: LocationRepository) : ILocationRepository
}