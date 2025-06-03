package com.projectlab.core.data.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.projectlab.core.data.service.GeocoderService
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.repository.LocationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationUtils @Inject constructor(
    @ApplicationContext val context: Context,
    private val geocoderService: GeocoderService,
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationRepository {

    override fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    override suspend fun reverseGeocodeLocation(location: Location): String = withContext(Dispatchers.IO) {
        try {
            val addresses = geocoderService.getFromLocation(location.latitude, location.longitude, 1)

            if (!addresses.isNullOrEmpty()) {
                val city = addresses[0].locality ?: addresses[0].subAdminArea
                val country = addresses[0].countryName
                "$city, $country"
            } else {
                "Unknown Location"
            }
        } catch (e: IOException) {
            "Location Unavailable"
        } catch (e: Exception) {
            "Location Error"
        }
    }

    override suspend fun getCoordinatesFromLocation(locationName: String): Location? =
        withContext(Dispatchers.IO) {
            try {

                val addressList = geocoderService.getFromLocationName(locationName, 1)

                return@withContext if (!addressList.isNullOrEmpty()) {
                    val address = addressList[0]
                    val latitude = address.latitude
                    val longitude = address.longitude
                    val city = address.locality ?: address.subAdminArea ?: ""
                    val country = address.countryName ?: ""
                    Location(
                        latitude = latitude,
                        longitude = longitude,
                        city = city,
                        country = country
                    )
                } else {
                    null
                }
            } catch (e: IOException) {
                null
            } catch (e: Exception) {
                null
            }
        }


    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? = suspendCoroutine { cont ->
        try {
            val cancellationTokenSource = CancellationTokenSource()
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { androidLocation ->
                if (androidLocation != null) {
                    cont.resume(
                        Location(
                            latitude = androidLocation.latitude,
                            longitude = androidLocation.longitude
                        )
                    )
                } else {
                    cont.resume(null)
                }
            }.addOnFailureListener { exception ->
                cont.resume(null)
            }
        } catch (e: Exception) {
            cont.resume(null)
        }
    }

}