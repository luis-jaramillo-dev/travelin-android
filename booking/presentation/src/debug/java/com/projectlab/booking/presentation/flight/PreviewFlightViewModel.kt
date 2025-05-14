package com.projectlab.booking.presentation.flight

import androidx.lifecycle.ViewModel
import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.model.CityLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// --- ViewModel for Preview ---
class PreviewFlightViewModel: IFlightViewModel {
    private val _uiState = MutableStateFlow(
        FlightViewModel.UiState(
        travelClass = FlightViewModel.UiTravelClass.ECONOMY,
        origin = "SYD",
        destination = "BKK",
        departureDate = "2025-06-01",
        returnDate = "2025-06-10",
        adults = 2,
        children = 1,
        infants = 0,
        nonStop = true,
        maxPrice = "600",
        flights = sample10Flights
    ))
    override val uiState: StateFlow<FlightViewModel.UiState> = _uiState.asStateFlow()
    override fun onClassSelected(c: FlightViewModel.UiTravelClass) {}
    override fun onOriginChange(i: String) {}
    override fun onOriginChosen(l: CityLocation) {}
    override fun onDestinationChange(i: String) {}
    override fun onDestinationChosen(l: CityLocation) {}
    override fun onDepartureDateSelected(d: String) {}
    override fun onReturnDateSelected(date: String?) {
    }
    override fun onPassengerCounts(a: Int, c: Int, i: Int) {}
    override fun onNonStopToggled() {}
    override fun onMaxPriceChange(p: String) {}
    override fun searchFlights(onComplete: ()->Unit) {}
    override fun loadMore() {
    }
}