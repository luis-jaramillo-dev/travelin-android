package com.projectlab.booking.presentation.itinerary

import com.projectlab.core.domain.entity.ItineraryEntity


data class ItinerariesUiState (
    val itineraries: List<ItineraryEntity> = emptyList(), // List of itineraries to display
    val isLoading: Boolean = false, // Indicates if data is being loaded.
    val error: String? = null // Error message in case of issues.
)


