package com.projectlab.booking.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.booking.presentation.screens.hotels.details.HotelDetailsUiState
import com.projectlab.booking.presentation.screens.hotels.search.HotelSearchUiState
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import com.projectlab.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val hotelsUseCases: HotelsUseCases
) : ViewModel() {

    private val _uiStateHotelSearch = MutableStateFlow(HotelSearchUiState())
    val uiStateHotelSearch: StateFlow<HotelSearchUiState> = _uiStateHotelSearch.asStateFlow()

    private val _uiStateHotelDetails = MutableStateFlow(HotelDetailsUiState())
    val uiStateHotelDetails: StateFlow<HotelDetailsUiState> = _uiStateHotelDetails.asStateFlow()


    fun onQueryChanged(newQuery: String) {
        _uiStateHotelSearch.update { it.copy(query = newQuery) }
    }

    fun onSearchSubmitted() {

        viewModelScope.launch {
            _uiStateHotelSearch.update { it.copy(isLoading = true) }
            try {

                when (val result =

                    hotelsUseCases.getHotelsByCity(_uiStateHotelSearch.value.query)) {
                    is Result.Success -> {

                        _uiStateHotelSearch.update { it.copy(hotels = result.data) }

                        println(_uiStateHotelSearch.value.hotels)
                    }

                    is Result.Error -> {
                        _uiStateHotelSearch.update { it.copy(error = "Unknown error") }
                    }

                }

            } catch (e: Exception) {
                println(e)
                _uiStateHotelSearch.update {
                    it.copy(
                        error = e.localizedMessage ?: "Unknown error"
                    )
                }
            } finally {
                _uiStateHotelSearch.update { it.copy(isLoading = false) }
            }
        }
    }

    fun showAllResults() {
        _uiStateHotelSearch.update { it.copy(showAllResults = true) }
    }

    fun getHotelDetails(hotelId: String) {
        println(_uiStateHotelSearch.value.hotels)
        val hotelFound = _uiStateHotelSearch.value.hotels.find { it.id == hotelId }
        println(hotelFound)
        _uiStateHotelDetails.update { it.copy(currentHotel = hotelFound) }
    }
}