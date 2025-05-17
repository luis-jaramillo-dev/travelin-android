package com.projectlab.booking.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.projectlab.core.domain.entity.ActivityEntity
import com.projectlab.core.domain.entity.FlightEntity
import com.projectlab.core.domain.entity.FlightSegmentEntity
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import com.projectlab.core.domain.repository.ActivityRepository
import com.projectlab.core.domain.repository.FlightRepository
import com.projectlab.core.domain.repository.FlightSegmentRepository
import com.projectlab.core.domain.repository.HotelRepository
import com.projectlab.core.domain.repository.ItineraryRepository
import com.projectlab.core.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

/**
 * ViewModel for testing purposes, to create test data in Firestore.
 * This is not used in the app, but it is useful for testing purposes.
 * We should used in the debug build variant only.
 *
 *  @author ricardoceadev
 */

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
            firstName = "LUKE",
            lastName = "SKYWALKER",
            countryCode = "900",
            phoneNumber = "777777",
            email = "THELASTJEDI@gmail.com",
            age = "54"
        )

        // We create the user in Firestore through the repository
        val userIdRes = userRepo.createUser(user)
        if (userIdRes.isFailure) {
            _seedResult.value = Result.failure(userIdRes.exceptionOrNull()!!)
            return@launch
        }

        // We get the user ID from the result
        val userId: EntityId = userIdRes.getOrThrow()

        // 2) We create a Itinerary:
        val itinerary = ItineraryEntity(
            //id                  = null,
            title = "Trip to a far far away galaxy...",
            startDate = Instant.now(),
            endDate = Instant.now().plusSeconds(2592000),
            totalItineraryPrice = 9000.0,
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
            airline = "Galaxy Airlines",
            flightNumber = "600",
            flightClass = "Economy",
            departureAirport = mapOf(
                "airportCodeRef" to "CHI", // TODO: Add id form firestore
                "time" to Timestamp(Date(System.currentTimeMillis()))
            ),
            arrivalAirport = mapOf(
                "airportCodeRef" to "EUR", // TODO: Add id form firestore
                "time" to Timestamp(Date(System.currentTimeMillis()))
            ),
            passengerNumber = mapOf(
                "adultsNumber" to 6,
                "kidsNumber" to 1,
                "babiesWithSitNumber" to 2,
                "babiesInArmsNumber" to 1
            ),
            price = 8000.0,
            userRef = userId,
            itineraryRef = itineraryId
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
            departureAirportCodeRef = EntityId("CHI"), // TODO: Add id form firestore
            arrivalAirportCodeRef = EntityId("USA"), // TODO: Add id form firestore
            departureTime = Instant.now(),
            arrivalTime = Instant.now().plusSeconds(7200),
            requiresPlaneChange = false,
            connectionInfo = mapOf(
                "nextAirline" to "BRA",
                "nextFlightNumber" to "500",
                "connectionGate" to "A1"
            ),
            userRef = userId,
            itineraryRef = itineraryId,
            flightRef = flightId
        )

        // We create the flight segment in Firestore through the repository
        val flightSegmentRes = flightSegmentRepo.createFlightSegment(flightSegment)
        if (flightSegmentRes.isFailure) {
            _seedResult.value = Result.failure(flightSegmentRes.exceptionOrNull()!!)
            return@launch
        }

        // 5) We create a Hotel:
        val hotel = HotelEntity(
            hotelName = "Hotel of the last jedi",
            hotelRoomNumber = 30,
            hotelPhone = 2222,
            locationRef = EntityId("location459"), // TODO: Add id form firestore
            guestName = "luke skywalker",
            guestPhone = 22222222,
            idNumber = 210,
            checkInDate = Instant.now(),
            checkOutDate = Instant.now().plusSeconds(86400),
            hotelPrice = 1500.0,
            userRef = userId,
            itineraryRef = itineraryId
        )

        // We create the hotel in Firestore through the repository
        val hotelRes = hotelRepo.createHotel(hotel)
        if (hotelRes.isFailure) {
            _seedResult.value = Result.failure(hotelRes.exceptionOrNull()!!)
            return@launch
        }

        // 6) We create an Activity:
        val activity = ActivityEntity(
            name = "Visit to the old republic museum",
            locationRef = EntityId("location789"), // TODO: Add id form firestore
            activityDate = Instant.now(),
            details = "Discover the history of the old republic",
            activityPrice = 40.0,
            userRef = userId,
            itineraryRef = itineraryId
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