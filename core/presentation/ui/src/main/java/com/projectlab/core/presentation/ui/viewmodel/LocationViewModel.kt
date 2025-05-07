package com.projectlab.core.presentation.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.presentation.ui.model.LocationData
import com.projectlab.core.presentation.ui.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUtils: LocationUtils
) : ViewModel() {

    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf<String>("Unknown location")
    val address: State<String> = _address

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation

        viewModelScope.launch {
            _address.value = locationUtils.reverseGeocodeLocation(newLocation)
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            if (locationUtils.hasLocationPermission(locationUtils.context)) {
                val currentLocation = locationUtils.getCurrentLocation()
                if (currentLocation != null) {
                    _location.value = currentLocation
                    _address.value = locationUtils.reverseGeocodeLocation(currentLocation)
                } else {
                    _address.value = "Could not get location"
                }
            } else {
                _address.value = "Location permission not granted"
            }
        }
    }
}