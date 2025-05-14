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
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.FlightRepository
import com.projectlab.core.domain.repository.FlightSegmentRepository
import com.projectlab.core.domain.repository.HotelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.util.Date

@HiltViewModel
class BookingViewModelTest @Inject constructor(
    private val userRepo : UserRepository,
    private val itineraryRepo : ItineraryRepository,
    private val flightRepo : FlightRepository,
    private val flightSegmentRepo : FlightSegmentRepository,
    private val hotelRepo : HotelRepository,
    private val activityRepo : ActivityRepository
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
            firstName           = "CARLOS III",
            lastName            = "INGLATERRA",
            countryCode         = "000",
            phoneNumber         = "1111",
            email               = "kingcharles@gmail.com"
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
            id                  = null,
            title               = "Trip to RIO DE JANEIRO",
            startDate           = Instant.now(),
            endDate             = Instant.now().plusSeconds(2592000),
            totalItineraryPrice = 9000.0,
            userRef             = userId
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
            id                  = null,
            airline             = "Brasil Airlines",
            flightNumber        = "800",
            flightClass         = "Premium",
            departureAirport    = mapOf(
                "airportCodeRef" to "LON", // TODO: Add id form firestore
                "time" to Timestamp(Date(System.currentTimeMillis()))
            ),
            arrivalAirport      = mapOf(
                "airportCodeRef" to "BRA", // TODO: Add id form firestore
                "time" to Timestamp(Date(System.currentTimeMillis()))
            ),
            passengerNumber     = mapOf(
                "adultsNumber" to 6,
                "kidsNumber" to 1,
                "babiesWithSitNumber" to 2,
                "babiesInArmsNumber" to 1
            ),
            price               = 8000.0,
            userRef             = userId,
            itineraryRef        = itineraryId
        )

        // We create the flight in Firestore through the repository
        val flightRes = flightRepo.createFlight(flight)
        if (flightRes.isFailure) {
            _seedResult.value = Result.failure(flightRes.exceptionOrNull()!!)
            return@launch
        }

        // We get the flight ID from the result
        val flightId: EntityId = flightRes.getOrThrow()

        // 4) We create a FlightSegment:
        val flightSegment = FlightSegmentEntity(
            id                  = null,
            departureAirportCodeRef = EntityId("CHI"), // TODO: Add id form firestore
            arrivalAirportCodeRef   = EntityId("ECU"), // TODO: Add id form firestore
            departureTime          = Instant.now(),
            arrivalTime            = Instant.now().plusSeconds(7200),
            requiresPlaneChange    = false,
            connectionInfo         = mapOf(
                "nextAirline" to "BRA",
                "nextFlightNumber" to "500",
                "connectionGate" to "A1"
            ),
            userRef             = userId,
            itineraryRef        = itineraryId,
            flightRef           = flightId
        )

        // We create the flight segment in Firestore through the repository
        val flightSegmentRes = flightSegmentRepo.createFlightSegment(flightSegment)
        if (flightSegmentRes.isFailure) {
            _seedResult.value = Result.failure(flightSegmentRes.exceptionOrNull()!!)
            return@launch
        }

        // 5) We create a Hotel:
        val hotel = HotelEntity(
            id                  = null,
            hotelName           = "Hotel de Rio",
            hotelRoomNumber     = 30,
            hotelPhone          = 2222,
            locationRef         = EntityId("location456"), // TODO: Add id form firestore
            guestName           = "ursula",
            guestPhone          = 22222222,
            idNumber            = 210,
            checkInDate         = Instant.now(),
            checkOutDate        = Instant.now().plusSeconds(86400),
            hotelPrice          = 1500.0,
            userRef             = userId,
            itineraryRef        = itineraryId
        )

        // We create the hotel in Firestore through the repository
        val hotelRes = hotelRepo.createHotel(hotel)
        if (hotelRes.isFailure) {
            _seedResult.value = Result.failure(hotelRes.exceptionOrNull()!!)
            return@launch
        }

        // 6) We create an Activity:
        val activity = ActivityEntity(
            id                  = null,
            name                = "Visit to the beach of Rio",
            locationRef         = EntityId("location789"), // TODO: Add id form firestore
            activityDate        = Instant.now(),
            details             = "Plays in the beach",
            activityPrice       = 40.0,
            userRef             = userId,
            itineraryRef        = itineraryId
        )

        // We create the activity in Firestore through the repository
        val activityRes = activityRepo.createActivity(activity)
        if (activityRes.isFailure) {
            _seedResult.value = Result.failure(activityRes.exceptionOrNull()!!)
            return@launch
        }

        // TODO: add other test data if it is needed

        _seedResult.value = Result.success(Unit) // indicate success, if all went well
    }
}
