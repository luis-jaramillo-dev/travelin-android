package com.projectlab.booking.presentation.screens.hotels.details

import com.projectlab.booking.models.HotelUi

data class DetailHotelState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val hotelUi: HotelUi? = null
)
