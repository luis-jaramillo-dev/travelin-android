package com.projectlab.booking.presentation.booking.hotels

import com.projectlab.booking.models.HotelUi
import java.time.LocalDate

data class BookingHotelState(
    val isLoading: Boolean = false,
    val error: String? = null,
    var guestName: String = "",
    var guestNumber: String = "",
    var countryCode: String = "",
    var email: String = "",
    var idNumber: Number? = null,
    val hotelUi: HotelUi? = null,
    val checkIn: LocalDate? = null,
    val checkOut: LocalDate? = null
)