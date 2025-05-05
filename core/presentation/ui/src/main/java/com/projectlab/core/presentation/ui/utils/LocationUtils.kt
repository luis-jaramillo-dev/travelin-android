package com.projectlab.core.presentation.ui.utils


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.projectlab.core.presentation.ui.model.LocationData
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class LocationUtils @Inject constructor (@ApplicationContext val context: Context) {

    private val _fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel){
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationData(latitude = it.latitude, longitude = it.longitude)
                    viewModel.updateLocation(location)
                }
            }

        }

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

        _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

    }

    fun hasLocationPermission(context: Context): Boolean {
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

    suspend fun reverseGeocodeLocation(location: LocationData): String = withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

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

    suspend fun getCoordinatesFromLocation(locationName: String): LocationData? = withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addressList = geocoder.getFromLocationName(locationName, 1)

            return@withContext if (!addressList.isNullOrEmpty()) {
                val address = addressList[0]
                val latitude = address.latitude
                val longitude = address.longitude
                val city = address.locality ?: address.subAdminArea ?: ""
                val country = address.countryName ?: ""

                LocationData(
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
    fun getCurrentLocation(): LocationData? {
        var locationData: LocationData? = null
        _fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                locationData = LocationData(
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }
        }
        return locationData
    }
}