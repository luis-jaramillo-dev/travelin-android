package flight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import model.Flight
import usecase.GetFlightsUseCase


@HiltViewModel
class FlightViewModel @Inject constructor(
    private val getFlightsUseCase: GetFlightsUseCase
) : ViewModel() {

    private val _flights = MutableStateFlow<List<Flight>>(emptyList())
    val flights: StateFlow<List<Flight>> = _flights

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

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