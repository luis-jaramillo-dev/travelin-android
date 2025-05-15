package com.projectlab.travelin_android.flight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.booking.presentation.flight.IFlightViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.projectlab.travelin_android.model.CityLocation
import javax.inject.Inject
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.model.FlightQueryParams
import com.projectlab.travelin_android.model.Price
import com.projectlab.travelin_android.model.TravelClass
import com.projectlab.travelin_android.usecase.GetFlightsUseCase
import com.projectlab.travelin_android.usecase.SearchCityLocationsUseCase
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/*
@HiltViewModel
class FlightViewModel @Inject constructor(

    private val searchCityLocationsUseCase: SearchCityLocationsUseCase,
    private val getFlightsUseCase: GetFlightsUseCase
) : ViewModel() {
    private val _originSuggestions = mutableStateListOf<CityLocation>()
    val originSuggestions: List<CityLocation> = _originSuggestions

    private val _destinationSuggestions = mutableStateListOf<CityLocation>()
    val destinationSuggestions: List<CityLocation> = _destinationSuggestions

    private val _citySuggestions = mutableStateListOf<CityLocation>()
    val citySuggestions: List<CityLocation> = _citySuggestions

    private val _flights = MutableStateFlow<List<Flight>>(emptyList())
    val flights: StateFlow<List<Flight>> = _flights

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    fun searchCityOrigin(keyword: String) {
        viewModelScope.launch {
            try {
                val results = searchCityLocationsUseCase(keyword)
                _originSuggestions.clear()
                _originSuggestions.addAll(results)
            } catch (e: Exception) {
                // manejar errores
            }
        }
    }

    fun searchCityDestination(keyword: String) {
        viewModelScope.launch {
            try {
                val results = searchCityLocationsUseCase(keyword)
                _destinationSuggestions.clear()
                _destinationSuggestions.addAll(results)
            } catch (e: Exception) {
                // manejar errores
            }
        }
    }
    fun clearOriginSuggestions() {
        _originSuggestions.clear()
    }

    fun clearDestinationSuggestions() {
        _destinationSuggestions.clear()
    }

    fun loadFlights(origin: String, destination: String, date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _flights.value = getFlightsUseCase(origin, destination, date)
            } catch (e: Exception) {
                _errorMessage.value = "Error loading flights: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}*/


/*@HiltViewModel
class FlightViewModel @Inject constructor(
    private val searchCityLocationsUseCase: SearchCityLocationsUseCase,
    private val getFlightsUseCase: GetFlightsUseCase
) : ViewModel(), IFlightViewModel {

    enum class UiTravelClass(val display: String) {
        ALL("All"), ECONOMY("Economy"),
        PREMIUM_ECONOMY("Premium Economy"), BUSINESS("Business"), FIRST("First")
    }

    data class UiState(
        val travelClass: UiTravelClass = UiTravelClass.ALL,
        val origin: String = "",
        val destination: String = "",
        val dateRange: Pair<String, String>? = null,
        val departureDate: String = "",
        val returnDate: String = "",
        val adults: Int = 1,
        val children: Int = 0,
        val infants: Int = 0,
        val totalPassengers: Int = adults + children + infants,
        val nonStop: Boolean = false,
        val maxPrice: String = "",
        val estimatedPrice: Price? = null,
        val originSuggestions: List<CityLocation> = emptyList(),
        val destinationSuggestions: List<CityLocation> = emptyList(),
        val flights: List<Flight> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    override val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    override fun onClassSelected(c: UiTravelClass) {
        _uiState.update { it.copy(travelClass = c) }
    }

    override fun onOriginChange(input: String) {
        _uiState.update { it.copy(origin = input) }
        searchCityLocations(input, isOrigin = true)
    }

    override fun onOriginChosen(loc: CityLocation) {
        _uiState.update { it.copy(origin = loc.iataCode, originSuggestions = emptyList()) }
    }

    override fun onDestinationChange(input: String) {
        _uiState.update { it.copy(destination = input) }
        searchCityLocations(input, isOrigin = false)
    }

    override fun onDestinationChosen(loc: CityLocation) {
        _uiState.update { it.copy(destination = loc.iataCode, destinationSuggestions = emptyList()) }
    }

    override fun onDepartureDateSelected(date: String) {
        _uiState.update { it.copy(departureDate = date) }
    }

    override fun onReturnDateSelected(date: String?) {
        _uiState.update {
            if (date != null) it.copy(returnDate = date)
            else it
        }
    }

    override fun onPassengerCounts(adults: Int, children: Int, infants: Int) {
        _uiState.update {
            it.copy(
                adults = adults.coerceAtLeast(1),
                children = children.coerceAtLeast(0),
                infants = infants.coerceAtLeast(0)
            )
        }
    }

    override fun onNonStopToggled() {
        _uiState.update { it.copy(nonStop = !it.nonStop) }
    }

    override fun onMaxPriceChange(price: String) {
        _uiState.update { it.copy(maxPrice = price) }
    }

    override fun searchFlights(onNext: () -> Unit) {
        val s = _uiState.value
        if (s.origin.isBlank() || s.destination.isBlank() || s.departureDate.isBlank()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val results = getFlightsUseCase(
                    FlightQueryParams(
                        originLocationCode = s.origin,
                        destinationLocationCode = s.destination,
                        departureDate = s.departureDate,
                        returnDate = s.returnDate.ifBlank { null },
                        adults = s.adults,
                        children = s.children,
                        infants = s.infants,
                        travelClass = if (s.travelClass == UiTravelClass.ALL) null
                        else TravelClass.valueOf(s.travelClass.name),
                        nonStop = s.nonStop,
                        maxPrice = s.maxPrice.toIntOrNull()
                    )
                )
                _uiState.update { it.copy(flights = results) }
                onNext?.invoke()
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.localizedMessage) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }


    private fun searchCityLocations(query: String, isOrigin: Boolean) {
        viewModelScope.launch {
            runCatching { searchCityLocationsUseCase(query) }
                .onSuccess { locs ->
                    if (isOrigin) {
                        _uiState.update { it.copy(originSuggestions = locs) }
                    } else {
                        _uiState.update { it.copy(destinationSuggestions = locs) }
                    }
                }
        }
    }

    fun clearOriginSuggestions() {
        _uiState.update { it.copy(originSuggestions = emptyList()) }
    }

    fun clearDestinationSuggestions() {
        _uiState.update { it.copy(destinationSuggestions = emptyList()) }
    }

    *//** Carga más resultados si existen *//*
    override fun loadMore() {
        // Por ahora, recolecta todos los vuelos ya cargados o realiza una nueva llamada
        // Aquí podrías implementar paginación real con flightRepository
        val current = _uiState.value
        _uiState.update { it.copy(*//* podrías ajustar flags de pagination *//*) }
    }
}*/
@HiltViewModel
class FlightViewModel @Inject constructor(
    private val searchCityLocationsUseCase: SearchCityLocationsUseCase,
    private val getFlightsUseCase: GetFlightsUseCase
) : ViewModel(), IFlightViewModel {

    enum class UiTravelClass(val display: String) {
        ALL("All"), ECONOMY("Economy"),
        PREMIUM_ECONOMY("Premium Economy"), BUSINESS("Business"), FIRST("First")
    }

    data class UiState(
        val travelClass: UiTravelClass = UiTravelClass.ALL,
        val origin: String = "",
        val destination: String = "",
        val departureDate: String = "",
        val returnDate: String = "",
        val dateRange: Pair<String, String>? = null,         // Nuevo
        val adults: Int = 1,
        val children: Int = 0,
        val infants: Int = 0,
        val totalPassengers: Int = adults + children + infants, // Nuevo
        val nonStop: Boolean = false,
        val maxPrice: String = "",
        val estimatedPrice: Price? = null,                   // Nuevo
        val originSuggestions: List<CityLocation> = emptyList(),
        val destinationSuggestions: List<CityLocation> = emptyList(),
        val flights: List<Flight> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    override val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    override fun onClassSelected(c: UiTravelClass) {
        _uiState.update { it.copy(travelClass = c) }
    }

    override fun onOriginChange(input: String) {
        _uiState.update { it.copy(origin = input) }
        searchCityLocations(input, isOrigin = true)
    }

    override fun onOriginChosen(loc: CityLocation) {
        _uiState.update { it.copy(origin = loc.iataCode, originSuggestions = emptyList()) }
    }

    override fun onDestinationChange(input: String) {
        _uiState.update { it.copy(destination = input) }
        searchCityLocations(input, isOrigin = false)
    }

    override fun onDestinationChosen(loc: CityLocation) {
        _uiState.update { it.copy(destination = loc.iataCode, destinationSuggestions = emptyList()) }
    }
    override fun onDateRangeSelected(range: Pair<String, String>) {
        _uiState.update { state ->
            state.copy(
                departureDate = range.first,
                returnDate    = range.second,
                dateRange     = range
            )
        }
    }

    override fun onDepartureDateSelected(date: String) {
        _uiState.update { state ->
            state.copy(
                departureDate = date,
                dateRange = date to (state.returnDate.ifBlank { date })
            )
        }
    }

    override fun onReturnDateSelected(date: String?) {
        _uiState.update { state ->
            val ret = date.orEmpty()
            state.copy(
                returnDate = ret,
                dateRange = state.departureDate.takeIf { it.isNotBlank() }?.let { dep ->
                    dep to ret
                }
            )
        }
    }

    override fun onPassengerCounts(adults: Int, children: Int, infants: Int) {
        _uiState.update {
            val a = adults.coerceAtLeast(1)
            val c = children.coerceAtLeast(0)
            val i = infants.coerceAtLeast(0)
            it.copy(
                adults = a,
                children = c,
                infants = i,
                totalPassengers = a + c + i
            )
        }
    }

    override fun onNonStopToggled() {
        _uiState.update { it.copy(nonStop = !it.nonStop) }
    }

    override fun onMaxPriceChange(price: String) {
        _uiState.update { it.copy(maxPrice = price) }
    }

    override fun searchFlights(onNext: () -> Unit) {
        val s = _uiState.value
        if (s.origin.isBlank() || s.destination.isBlank() || s.departureDate.isBlank()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val results = getFlightsUseCase(
                    FlightQueryParams(
                        originLocationCode = s.origin,
                        destinationLocationCode = s.destination,
                        departureDate = s.departureDate,
                        returnDate = s.returnDate.ifBlank { null },
                        adults = s.adults,
                        children = s.children,
                        infants = s.infants,
                        travelClass = if (s.travelClass == UiTravelClass.ALL) null
                        else TravelClass.valueOf(s.travelClass.name),
                        nonStop = s.nonStop,
                        maxPrice = s.maxPrice.toIntOrNull()
                    )
                )
                _uiState.update {
                    it.copy(
                        flights = results,
                        estimatedPrice = results.firstOrNull()?.price
                    )
                }
                onNext()
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.localizedMessage) }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun searchCityLocations(query: String, isOrigin: Boolean) {
        viewModelScope.launch {
            runCatching { searchCityLocationsUseCase(query) }
                .onSuccess { locs ->
                    if (isOrigin) _uiState.update { it.copy(originSuggestions = locs) }
                    else _uiState.update { it.copy(destinationSuggestions = locs) }
                }
        }
    }

    override fun loadMore() {
        // Implementa paginación si la necesitas
    }
}


