package com.projectlab.core.presentation.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.domain.model.Location
import com.projectlab.core.domain.repository.LocationRepository
import com.projectlab.core.presentation.designsystem.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _location = mutableStateOf<Location?>(null)
    val location: State<Location?> = _location

    private val _address = mutableStateOf<String>("Unknown location")
    val address: State<String> = _address

    fun hasLocationPermission(): Boolean {
        return locationRepository.hasLocationPermission()
    }

    suspend fun reverseGeocodeLocation(location: Location): String {
        return locationRepository.reverseGeocodeLocation(location)
    }

    suspend fun getCoordinatesFromLocation(locationName: String): Location? {
        return locationRepository.getCoordinatesFromLocation(locationName)
    }

    fun updateLocation(newLocation: Location) {
        _location.value = newLocation

        viewModelScope.launch {
            _address.value = locationRepository.reverseGeocodeLocation(newLocation)
        }
    }

    fun setUnknownLocation(context: Context) {
        _location.value = null
        _address.value = context.getString(R.string.unknown_location)
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            if (locationRepository.hasLocationPermission()) {
                val currentLocation = locationRepository.getCurrentLocation()
                if (currentLocation != null) {
                    _location.value = currentLocation
                    _address.value = locationRepository.reverseGeocodeLocation(currentLocation)
                } else {
                    _address.value = "Could not get location"
                }
            } else {
                _address.value = "Location permission not granted"
            }
        }
    }
}