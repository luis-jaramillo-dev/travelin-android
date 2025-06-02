package com.projectlab.booking.presentation.booking.hotels

import com.projectlab.core.domain.model.Hotel

data class BookingHotelState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentHotel: Hotel? = null,
    var guestName: String = "",
    var guestNumber: String = "",
    var countryCode: String = "",
    var email: String = "",
    var idNumber: Number? = null
)