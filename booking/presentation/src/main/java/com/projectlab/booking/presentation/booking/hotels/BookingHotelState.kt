package com.projectlab.booking.presentation.booking.hotels

import com.projectlab.booking.models.HotelUi

data class BookingHotelState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var guestName: String = "",
    var guestNumber: String = "",
    var countryCode: String = "",
    var email: String = "",
    var idNumber: Number? = null,
    val hotelUi: HotelUi? = null
)