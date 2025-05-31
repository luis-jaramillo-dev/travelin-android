package com.projectlab.core.data.service

import android.content.Context
import android.location.Address
import android.location.Geocoder
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject

class GeocoderServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GeocoderService {

    private val geocoder = Geocoder(context, Locale.getDefault())

    override fun getFromLocation(
        latitude: Double,
        longitude: Double,
        maxResults: Int
    ): MutableList<Address>? {
        return geocoder.getFromLocation(latitude, longitude, 1)
    }

    override fun getFromLocationName(
        locationName: String,
        maxResults: Int
    ) : MutableList<Address>? {
        return geocoder.getFromLocationName(locationName, maxResults)
    }

}