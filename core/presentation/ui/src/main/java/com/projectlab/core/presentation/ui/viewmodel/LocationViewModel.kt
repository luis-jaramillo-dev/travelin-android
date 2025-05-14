package com.projectlab.core.presentation.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.domain.model.Location
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.ui.utils.LocationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationUtils: LocationUtils
) : ViewModel() {

    private val _location = mutableStateOf<Location?>(null)
    val location: State<Location?> = _location

    private val _address = mutableStateOf<String>("Unknown location")
    val address: State<String> = _address

    fun updateLocation(newLocation: Location) {
        _location.value = newLocation

        viewModelScope.launch {
            _address.value = locationUtils.reverseGeocodeLocation(newLocation)
        }
    }

    fun setUnknownLocation(context: Context) {
        _location.value = null
        _address.value = context.getString(R.string.unknown_location)
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