package com.projectlab.booking.presentation.flight

import com.projectlab.travelin_android.flight.FlightViewModel
import com.projectlab.travelin_android.model.CityLocation
import kotlinx.coroutines.flow.StateFlow

interface IFlightViewModel {
    val uiState: StateFlow<FlightViewModel.UiState>

    fun onClassSelected(travelClass: FlightViewModel.UiTravelClass)
    fun onOriginChange(text: String)
    fun onOriginChosen(location: CityLocation)
    fun onDestinationChange(text: String)
    fun onDestinationChosen(location: CityLocation)
    fun onDepartureDateSelected(date: String)
    fun onReturnDateSelected(date: String?)
    fun onPassengerCounts(adults: Int, children: Int, infants: Int)
    fun onNonStopToggled()
    fun onMaxPriceChange(price: String)
    fun searchFlights(onNext: () -> Unit={})
    fun loadMore()
}