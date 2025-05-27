package com.projectlab.booking.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.booking.presentation.screens.hotels.details.HotelDetailsUiState
import com.projectlab.booking.presentation.screens.hotels.search.HotelSearchUiState
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.Response
import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import com.projectlab.core.domain.use_cases.users.UsersUseCases
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
    private val hotelsUseCases: HotelsUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    private val _uiStateHotelSearch = MutableStateFlow(HotelSearchUiState())
    val uiStateHotelSearch: StateFlow<HotelSearchUiState> = _uiStateHotelSearch.asStateFlow()

    private val _uiStateHotelDetails = MutableStateFlow(HotelDetailsUiState())
    val uiStateHotelDetails: StateFlow<HotelDetailsUiState> = _uiStateHotelDetails.asStateFlow()

    private var user by mutableStateOf(
        User()
    )
        private set

    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById("7wmWegYUANOFv8ZvKZN1GzHoqvV2").collect() {
            user = it
        }
    }

    fun onQueryChanged(newQuery: String) {
        _uiStateHotelSearch.update { it.copy(query = newQuery) }
    }

    fun onSearchSubmitted() {

        viewModelScope.launch {
            _uiStateHotelSearch.update { it.copy(isLoading = true) }
            try {
                when (
                    val result = hotelsUseCases.getHotelsByCity(_uiStateHotelSearch.value.query)) {
                    is Result.Success -> {

                        val newFavoriteHotel = hotelsListWithFavorite(
                            user.favoritesHotels,
                            result.data.toMutableList()
                        )


                        _uiStateHotelSearch.update { it.copy(hotels = newFavoriteHotel) }
                        println(_uiStateHotelSearch.value.hotels)
                    }

                    is Result.Error -> {
                        _uiStateHotelSearch.update { it.copy(error = "Unknown error") }
                    }
                }
            } catch (e: Exception) {
                println(e)
            } finally {
                _uiStateHotelSearch.update { it.copy(isLoading = false) }
            }
        }
    }

    fun showAllResults() {
        _uiStateHotelSearch.update { it.copy(showAllResults = true) }
    }

    fun getHotelDetails(hotelId: String) {
        val hotelFound = _uiStateHotelSearch.value.hotels.find { it.id == hotelId }
        _uiStateHotelDetails.update { it.copy(currentHotel = hotelFound) }
    }

    fun favoriteHotel(hotelId: String) {
        viewModelScope.launch {
            try {
                when (hotelsUseCases.favoriteHotel("7wmWegYUANOFv8ZvKZN1GzHoqvV2", hotelId)) {
                    is Result.Success -> {

                        val updatedHotels = _uiStateHotelSearch.value.hotels.map {
                            if (it.id == hotelId) {
                                it.copy(isFavourite = true)
                            } else {
                                it
                            }
                        }
                        _uiStateHotelSearch.update { it.copy(hotels = updatedHotels) }
                    }

                    is Result.Error -> {
                        _uiStateHotelSearch.update { it.copy(error = "Unknown error") }
                    }

                }


            } catch (e: Exception) {
                println(e)

            } finally {
                _uiStateHotelSearch.update { it.copy(isLoading = false) }
            }
        }
    }

    fun unfavoriteHotel(hotelId: String) {
        viewModelScope.launch {
            try {
                when (hotelsUseCases.unfavoriteHotel("7wmWegYUANOFv8ZvKZN1GzHoqvV2", hotelId)) {
                    is Result.Success -> {

                        val updatedHotels = _uiStateHotelSearch.value.hotels.map {
                            if (it.id == hotelId) {
                                it.copy(isFavourite = false)
                            } else {
                                it
                            }
                        }
                        _uiStateHotelSearch.update { it.copy(hotels = updatedHotels) }
                    }

                    is Result.Error -> {
                        _uiStateHotelSearch.update { it.copy(error = "Unknown error") }
                    }

                }


            } catch (e: Exception) {
                println(e)

            } finally {
                _uiStateHotelSearch.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun hotelsListWithFavorite(
        hotelsFavorites: List<String>,
        hotelsList: MutableList<Hotel>
    ): MutableList<Hotel> {

        for (i in 0 until hotelsList.size) {
            val hotel = hotelsList[i]
            if (hotelsFavorites.contains(hotel.id)) {
                hotelsList[i].isFavourite = true
            }
        }

        return hotelsList.sortedBy { it.isFavourite }.reversed() as MutableList<Hotel>
    }


}