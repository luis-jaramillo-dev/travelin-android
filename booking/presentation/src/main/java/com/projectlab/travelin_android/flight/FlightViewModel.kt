package com.projectlab.travelin_android.flight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.projectlab.travelin_android.model.CityLocation
import javax.inject.Inject
import com.projectlab.travelin_android.model.Flight
import com.projectlab.travelin_android.usecase.GetFlightsUseCase
import com.projectlab.travelin_android.usecase.SearchCityLocationsUseCase


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
}