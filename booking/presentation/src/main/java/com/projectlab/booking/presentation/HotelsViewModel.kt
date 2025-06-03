package com.projectlab.booking.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.booking.models.toHotelUi
import com.projectlab.booking.presentation.screens.hotels.details.DetailHotelState
import com.projectlab.booking.presentation.screens.hotels.search.SearchHotelState
import com.projectlab.core.domain.model.Hotel
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

    private val _uiStateHotelSearch = MutableStateFlow(SearchHotelState())
    val uiStateHotelSearch: StateFlow<SearchHotelState> = _uiStateHotelSearch.asStateFlow()

    private val _uiStateHotelDetails = MutableStateFlow(DetailHotelState())
    val uiStateHotelDetails: StateFlow<DetailHotelState> = _uiStateHotelDetails.asStateFlow()

    // TODO: CHANGE FOR PROTO IMPL
    val userId = "7wmWegYUANOFv8ZvKZN1GzHoqvV2"

    var detailHotelState by mutableStateOf(DetailHotelState())
        private set

    var searchHotelState by mutableStateOf(SearchHotelState())
        private set

    private var user by mutableStateOf(
        User()
    )
        private set

    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(userId).collect() {
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
        _uiStateHotelDetails.update { it.copy(hotelUi = hotelFound!!.toHotelUi()) }
    }

    fun favoriteHotel(hotelId: String) {
        viewModelScope.launch {
            try {
                when (hotelsUseCases.favoriteHotel(userId, hotelId)) {
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
                when (hotelsUseCases.unfavoriteHotel(userId, hotelId)) {
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