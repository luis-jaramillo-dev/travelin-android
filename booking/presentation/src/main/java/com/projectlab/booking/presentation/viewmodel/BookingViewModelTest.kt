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
import com.projectlab.core.domain.entity.UserEntity
import com.projectlab.core.domain.model.EntityId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant

@HiltViewModel
class BookingViewModelTest @Inject constructor(
    private val userRepo : UserRepository,
    private val itineraryRepo : ItineraryRepository
    // TODO : add other repositories as needed: flight, hotel, etc.
) : ViewModel(){

    // we define a state in order to inform the UI about the data was successfully loaded or not
    private val _seedResult = MutableStateFlow<Result<Unit>?>(null)
    val seedResult = _seedResult.asStateFlow()

    // hardcoding and persist data for Firestore testing purposes (through data layer)
    @RequiresApi(Build.VERSION_CODES.O)
    fun setupTestData() = viewModelScope.launch {
        // Hardcode user data
        val user = UserEntity(
            firstName = "Carlos",
            lastName = "Palacios",
            countryCode = "56",
            phoneNumber = "999999999",
            email = "carlitospalacios@gmail.com"
        )

        // 1) We create a user and get the domain ID
        val UserIdRes = userRepo.createUser(user)
        if (UserIdRes.isFailure) {
            _seedResult.value = Result.failure(UserIdRes.exceptionOrNull()!!)
            return@launch
        }

        val userId: EntityId = UserIdRes.getOrThrow()

        // 2) We create a Itinerary:
        val itinerary = ItineraryEntity(
            id = null,
            title = "Barcelona Trip",
            // TODO : Check Timestamp.now()
            //startDate = Timestamp.now(),
            //endDate = Timestamp.now(),
            startDate = Instant.now(),
            endDate = Instant.now(),
            totalItineraryPrice = 1000.0,
            userRef = userId
        )

        val itinRes = itineraryRepo.createItinerary(itinerary)
        if (itinRes.isFailure) {
            _seedResult.value = Result.failure(itinRes.exceptionOrNull()!!)
            return@launch
        }

        _seedResult.value = Result.success(Unit)
    }


//    fun setupTestData() = viewModelScope.launch {
//        // 1 hardcode user data
//        val user = UserEntity(
//            firstName = "Carlos",
//            lastName = "Rodriguez",
//            countryCode = 56,
//            phoneNumber = 999999999,
//            email = "Carlos@example.com"
//        )
//
//        val userId = userRepo.createUser(user).getOrNull() ?: return@launch
//
//        // 2 hardcode itinerary data
//        val userRef = FirebaseFirestore.getInstance().collection("Users").document(userId)
//        val itinerary = ItineraryEntity(
//            title = "Barcelona Trip",
//            startDate = Timestamp.now(),
//            endDate = Timestamp.now(),
//            totalItineraryPrice = 1000.0,
//            userRef = userRef
////            flightDetails = listOf("Flight 1", "Flight 2"),
////            hotelDetails = listOf("Hotel 1", "Hotel 2"),
////            activities = listOf("Activity 1", "Activity 2"),
//
//        )
//
//        val itineraryId = itineraryRepo.createItinerary(itinerary).getOrNull() ?: return@launch
//
//        // TODO: add other test data as needed: flights, hotels, etc.

}
