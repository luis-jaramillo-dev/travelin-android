package com.projectlab.core.data

import android.content.Context
import android.location.Address
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.gms.location.FusedLocationProviderClient
import com.projectlab.core.data.service.GeocoderService
import com.projectlab.core.data.utils.LocationUtils
import com.projectlab.core.domain.model.Location
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class LocationUtilsTest {
    private val location = Location(latitude = -33.43778, longitude = -70.65028)

    private val context = mock<Context>()

    private val geocoderService = mock<GeocoderService>()

    private val fusedLocationClient = mock<FusedLocationProviderClient>()

    private lateinit var locationUtils: LocationUtils

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        locationUtils = LocationUtils(
            context = context,
            geocoderService = geocoderService,
            fusedLocationClient = fusedLocationClient
        )
    }

    @Test
    fun convertsCoordinatesToAddress() = runBlocking  {

        val fakeAddress = mock(Address::class.java).apply {
            Mockito.`when`(this.locality).thenReturn("Santiago")
            Mockito.`when`(this.countryName).thenReturn("Chile")
        }

        val fakeAddressList = mutableListOf(fakeAddress)

        Mockito.`when`(geocoderService.getFromLocation(location.latitude, location.longitude, 1))
            .thenReturn(fakeAddressList)

        val address = locationUtils.reverseGeocodeLocation(location)
        assertEquals("Santiago, Chile", address)

    }

    @Test
    fun convertsAddressToCoordinates() = runBlocking {
        val location = locationUtils.getCoordinatesFromLocation("Santiago, Chile")
        assertEquals(-33.43778, location?.latitude)
        assertEquals(-70.65028, location?.longitude)

    }
}