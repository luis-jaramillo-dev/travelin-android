package com.projectlab.booking.presentation.itinerary



data class ItinerariesUiState (
    val itineraries: List<String> = emptyList(), // List of itineraries to display TODO: implement  DTO
    val isLoading: Boolean = false, // Indicates if data is being loaded.
    val error: String? = null // Error message in case of issues.
)


