package com.narcis.openweatherinterview.data.dataSource

import android.Manifest
import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationToken
import com.narcis.model.weatherActions.LocationModel
import com.narcis.openweatherinterview.data.model.toDataModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class  LocationRemoteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext),
    private val cancellationToken: CancellationToken,
    private val locationSettingsClient: SettingsClient,
    private val locationSettingsRequest: LocationSettingsRequest
)  : ILocationRemoteDataSource{
    override fun getCurrentLocation(): Flow<LocationModel> {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        return flow {
//            println(" until here ... ")
            checkLocationEnabled()
            fusedLocationProviderClient.lastLocation.await().let{
              emit(it.toDataModel())
            }
//            fusedLocationProviderClient.getCurrentLocation(
//                LocationRequest.PRIORITY_NO_POWER,
//                cancellationToken
//            ).await().let {
//                emit(it.toDataModel())
//            }
        }
    }


    private suspend fun checkLocationEnabled() {

        runCatching {
            locationSettingsClient.checkLocationSettings(locationSettingsRequest).await()
        }.getOrElse {
            throw when(it) {
                is ResolvableApiException -> Exception("the location service is off !")
                else -> it
            }
        }
    }
}