package com.projectlab.booking.presentation.flight

import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Flight

data class FlightUiState(
    val travelClass: FlightViewModel.UiTravelClass = FlightViewModel.UiTravelClass.ECONOMY,
    val origin: String = "",
    val destination: String = "",
    val originSuggestions: List<CityLocation> = emptyList(),
    val destinationSuggestions: List<CityLocation> = emptyList(),
    val departureDate: String = "",
    val returnDate: String? = null,
    val adults: Int = 1,
    val children: Int = 0,
    val infants: Int = 0,
    val nonStop: Boolean = false,
    val maxPrice: String = "",
    val flights: List<Flight> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)