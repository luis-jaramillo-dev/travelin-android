package com.projectlab.booking.presentation.screens.hotels.search

import com.projectlab.core.domain.model.Hotel


data class SearchHotelState(
    val query: String = "",
    val hotels: List<Hotel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAllResults: Boolean = false,
)