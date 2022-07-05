package com.narcis.openweatherinterview.data.repository.locationRepository

import android.Manifest
import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.CancellationToken
import com.narcis.openweatherinterview.data.model.LocationModel
import com.narcis.openweatherinterview.domain.ResultWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    @ApplicationContext private val context : Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext),
    private val locationSettingsClient: SettingsClient,
    private val cancellationToken: CancellationToken,
) : ILocationRepository{
    override fun getCurrentLocation(): Flow<ResultWrapper<LocationModel>>? {
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)


        return flow {
            fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationToken).await().let {
                emit(it.toDataModel())
            }
        }
    }

    private suspend fun requiredLocationEnabled() {
        kotlin.runCatching {

        }
    }
}