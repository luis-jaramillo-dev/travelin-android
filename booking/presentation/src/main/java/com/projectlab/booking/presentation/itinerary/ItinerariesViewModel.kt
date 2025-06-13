package com.projectlab.booking.presentation.itinerary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.itineraries.GetAllItinerariesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ItinerariesViewModel @Inject constructor(
    private val getAllItinerariesUseCase: GetAllItinerariesUseCase,
    private val userSessionProvider: UserSessionProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ItinerariesUiState(isLoading = true))
    val uiState: StateFlow<ItinerariesUiState> = _uiState

    fun loadItineraries() {
        viewModelScope.launch {
            getAllItinerariesUseCase(userSessionProvider.getUserSessionId().toString())
                .onSuccess { list ->
                    _uiState.value = ItinerariesUiState(itineraries = list)
                }
                .onFailure { error ->
                    _uiState.value = ItinerariesUiState(error = error.message)
                }
        }
    }

//    init {
//        viewModelScope.launch {
//            getAllItinerariesUseCase(userSessionProvider.getUserSessionId().toString())
//                .onSuccess { list ->
//                    _uiState.value = ItinerariesUiState(itineraries = list)
//                }
//                .onFailure { error ->
//                    _uiState.value = ItinerariesUiState(error = error.message)
//                }
//        }
//    }
}
