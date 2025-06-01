package com.projectlab.core.data.service

import android.location.Address

interface GeocoderService {
    fun getFromLocation(latitude: Double, longitude: Double, maxResults: Int): MutableList<Address>?
    fun getFromLocationName(locationName: String, maxResults: Int): MutableList<Address>?
}