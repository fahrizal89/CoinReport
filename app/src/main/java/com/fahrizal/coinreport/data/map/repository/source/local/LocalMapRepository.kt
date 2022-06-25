package com.fahrizal.coinreport.data.map.repository.source.local

import android.annotation.SuppressLint
import android.content.Context
import com.fahrizal.coinreport.data.map.model.Location
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LocalMapRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @SuppressLint("MissingPermission")
    fun getLatitudeLongitude(): Flow<Location> {
        return callbackFlow {
            val fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    trySend(Location(it.latitude, it.longitude))
                }
                .addOnFailureListener {
                    trySend(Location(0.0, 0.0))
                }

            awaitClose { Location(0.0, 0.0) }
        }
    }
}