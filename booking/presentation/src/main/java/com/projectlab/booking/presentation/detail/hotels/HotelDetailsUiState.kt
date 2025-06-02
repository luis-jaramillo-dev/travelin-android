package com.projectlab.booking.presentation.screens.hotels.details

import com.projectlab.core.domain.model.Hotel


data class HotelDetailsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentHotel: Hotel? = null
)
