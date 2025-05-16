package com.projectlab.booking.presentation.flight

import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.model.CityLocation
import com.projectlab.travelin_android.model.Price
import com.projectlab.travelin_android.model.TravelClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeFlightViewModel : IFlightViewModel {
    private val _uiState = MutableStateFlow(
        FlightViewModel.UiState(
            travelClass = FlightViewModel.UiTravelClass.BUSINESS,
            origin = "SCL",
            destination = "JFK",
            departureDate = "2025-04-12",
            returnDate    = "2025-04-19",
            dateRange     = "12 Apr" to "19 Apr",
            adults        = 2,
            children      = 1,
            infants       = 0,
            // totalPassengers se calcula automáticamente en UiState
            nonStop       = true,
            maxPrice      = "500",
            estimatedPrice = Price(amount = "480", currency = "USD"),
            originSuggestions = listOf(
                CityLocation(city = "Santiago", iataCode = "SCL"),
                CityLocation(city = "Buenos Aires", iataCode = "EZE")
            ),
            destinationSuggestions = listOf(
                CityLocation(city = "New York", iataCode = "JFK"),
                CityLocation(city = "Los Angeles", iataCode = "LAX")
            ),
            flights = emptyList(),
            isLoading = false,
            errorMessage = null
        )
    )
    override val uiState: StateFlow<FlightViewModel.UiState> = _uiState

    override fun onClassSelected(travelClass: FlightViewModel.UiTravelClass) { /* no-op */ }
    override fun onOriginChange(text: String)                { /* no-op */ }
    override fun onOriginChosen(location: CityLocation)      { /* no-op */ }
    override fun onDestinationChange(text: String)           { /* no-op */ }
    override fun onDestinationChosen(location: CityLocation) { /* no-op */ }
    override fun onDepartureDateSelected(date: String)       { /* no-op */ }
    override fun onReturnDateSelected(date: String?)         { /* no-op */ }
    override fun onDateRangeSelected(range: Pair<String, String>) { /* no-op */ }
    override fun onPassengerCounts(adults: Int, children: Int, infants: Int) { /* no-op */ }
    override fun onNonStopToggled()                          { /* no-op */ }
    override fun onMaxPriceChange(price: String)             { /* no-op */ }
    override fun searchFlights(onNext: () -> Unit)           { /* no-op */ }
    override fun loadMore()                                  { /* no-op */ }
}

