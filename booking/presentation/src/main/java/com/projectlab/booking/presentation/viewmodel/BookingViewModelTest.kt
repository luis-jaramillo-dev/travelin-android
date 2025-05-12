package com.projectlab.booking.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.repository.UserRepository
import com.projectlab.core.domain.entity.ItineraryEntity
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.FlightRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.util.Date

@HiltViewModel
class BookingViewModelTest @Inject constructor(
    private val userRepo : UserRepository,
    private val itineraryRepo : ItineraryRepository,
    private val flightRepo : FlightRepository
    // TODO : add other repositories as needed: flight, hotel, etc.
) : ViewModel(){

    // we define a state in order to inform the UI about the data was successfully loaded or not
    private val _seedResult = MutableStateFlow<Result<Unit>?>(null)
    val seedResult = _seedResult.asStateFlow()

    // hardcoding and persist data for Firestore testing purposes (through data layer)
    @RequiresApi(Build.VERSION_CODES.O)
    fun setupTestData() = viewModelScope.launch {
        // 1) We create a user and get the domain ID
        // Hardcode user data
        val user = UserEntity(
            firstName = "LEO XIV",
            lastName = "XIV PP",
            countryCode = "01",
            phoneNumber = "99999999",
            email = "thepope@gmail.com"
        )

        // We create the user in Firestore through the repository
        val UserIdRes = userRepo.createUser(user)
        if (UserIdRes.isFailure) {
            _seedResult.value = Result.failure(UserIdRes.exceptionOrNull()!!)
            return@launch
        }

        // We get the user ID from the result
        val userId: EntityId = UserIdRes.getOrThrow()

        // 2) We create a Itinerary:
        val itinerary = ItineraryEntity(
            id = null,
            title = "Trip to Vatican",
            // TODO : Check Timestamp.now()
            //startDate = Timestamp(Date.from(Instant.now())),
            //endDate = Timestamp(Date.from(Instant.now())),
            startDate = Instant.now(),
            endDate = Instant.now(),
            totalItineraryPrice = 4000.0,
            userRef = userId
        )

        // We create the itinerary in Firestore through the repository
        val itinRes = itineraryRepo.createItinerary(itinerary)
        if (itinRes.isFailure) {
            _seedResult.value = Result.failure(itinRes.exceptionOrNull()!!)
            return@launch
        }

        // We get the itinerary ID from the result
        val itineraryId: EntityId = itinRes.getOrThrow()

        // 3) We create a flight:
        val flight = FlightEntity(
            id = null,
            airline = "Sky Airlines",
            flightNumber = "100",
            flightClass = "Premium",
            departureAirport = mapOf(
                "airportCodeRef" to "CLP",
                "time" to Timestamp(Date(System.currentTimeMillis()))
            ),
            arrivalAirport = mapOf(
                "airportCodeRef" to "ROM",
                "time" to Timestamp(Date(System.currentTimeMillis()))
            ),
            passengerNumber = mapOf(
                "adultsNumber" to 1,
                "kidsNumber" to 3,
                "babiesWithSitNumber" to 5,
                "babiesInArmsNumber" to 1
            ),
            price = 2200.0,
            userRef = userId,
            itineraryRef = itineraryId
        )

        // We create the flight in Firestore through the repository
        val flightRes = flightRepo.createFlight(flight)
        if (flightRes.isFailure) {
            _seedResult.value = Result.failure(flightRes.exceptionOrNull()!!)
            return@launch
        }

        _seedResult.value = Result.success(Unit)
    }

// TODO: add other test data as I could: flights, hotels, etc.

}
