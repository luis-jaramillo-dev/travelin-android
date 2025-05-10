package com.projectlab.booking.presentation.viewmodel

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

@HiltViewModel
class BookingViewModelTest @Inject constructor(
    private val userRepo : UserRepository,
    private val itineraryRepo : ItineraryRepository
    // TODO : add other repositories as needed: flight, hotel, etc.
) : ViewModel(){

    fun setupTestData() = viewModelScope.launch {
        // 1 hardcode user data
        val user = UserEntity(
            firstName = "Carlos",
            lastName = "Rodriguez",
            countryCode = 56,
            phoneNumber = 999999999,
            email = "Carlos@example.com"
        )

        val userId = userRepo.createUser(user).getOrNull() ?: return@launch

        // 2 hardcode itinerary data
        val userRef = FirebaseFirestore.getInstance().collection("Users").document(userId)
        val itinerary = ItineraryEntity(
            title = "Barcelona Trip",
            startDate = Timestamp.now(),
            endDate = Timestamp.now(),
            totalItineraryPrice = 1000.0,
            userRef = userRef
//            flightDetails = listOf("Flight 1", "Flight 2"),
//            hotelDetails = listOf("Hotel 1", "Hotel 2"),
//            activities = listOf("Activity 1", "Activity 2"),

        )

        val itineraryId = itineraryRepo.createItinerary(itinerary).getOrNull() ?: return@launch

        // TODO: add other test data as needed: flights, hotels, etc.

    }

}
