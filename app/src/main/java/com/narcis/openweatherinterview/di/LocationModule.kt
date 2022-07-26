package com.narcis.openweatherinterview.di

import android.content.Context
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
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

    @Provides
    fun provideLocationRequest() : LocationRequest {
        return LocationRequest().apply {
            priority - LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    @Provides
    fun locationSettingsRequest(locationRequest: LocationRequest) : LocationSettingsRequest {
        return LocationSettingsRequest.Builder()
            .setAlwaysShow(true)
            .addLocationRequest(locationRequest)
            .build()
    }

    @Provides
    internal fun providesLocationSettingsClient( @ApplicationContext context: Context) : SettingsClient {
        return LocationServices.getSettingsClient(context)
    }

    @Provides
    internal fun providesCancellationToken() : CancellationToken {
        return CancellationTokenSource().token
    }
}