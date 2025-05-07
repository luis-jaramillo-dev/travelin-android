package flight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import model.Flight
import usecase.GetFlightsUseCase

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val getFlightsUseCase : GetFlightsUseCase
): ViewModel() {

    var flights by mutableStateOf<List<Flight>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set

    fun loadFlights(origin: String, destination: String, date: String){
        viewModelScope.launch{
            isLoading = true
            flights = getFlightsUseCase(origin,destination,date)
            isLoading = false
        }
    }
}