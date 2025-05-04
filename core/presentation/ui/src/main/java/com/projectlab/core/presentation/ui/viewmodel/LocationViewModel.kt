package com.projectlab.core.presentation.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.projectlab.core.presentation.ui.model.LocationData

class LocationViewModel: ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf<String?>(null)
    val address: State<String?> = _address

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }

    fun updateAddress(address: String) {
        _address.value = address
    }
}