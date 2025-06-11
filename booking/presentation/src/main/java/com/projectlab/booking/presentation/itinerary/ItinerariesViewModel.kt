package com.projectlab.booking.presentation.itinerary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//@HiltViewModel
//class ItinerariesViewModel @Inject constructor(
//    private val getItinerariesUseCase: GetItinerariesUseCase
//): ViewModel() {
//
//    private val _uiState = MutableStateFlow(ItinerariesUiState(isLoading = true))
//    val uiState: StateFlow<ItinerariesUiState> = _uiState
//
//    init {
//        viewModelScope.launch {
//            getItinerariesUseCase()
//                .onSuccess { list ->
//                    _uiState.value = ItinerariesUiState(itineraries = list)
//                }
//                .onFailure { error ->
//                    _uiState.value = ItinerariesUiState(error = error.message)
//                }
//        }
//    }
//}
