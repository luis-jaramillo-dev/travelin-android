package com.projectlab.booking.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectlab.booking.models.toHotelUi
import com.projectlab.booking.presentation.booking.hotels.BookingHotelState
import com.projectlab.booking.presentation.screens.hotels.details.DetailHotelState
import com.projectlab.booking.presentation.screens.hotels.search.SearchHotelState
import com.projectlab.booking.utils.StringValue.StringResource
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import com.projectlab.core.domain.use_cases.location.GetCoordinatesFromCityUseCase
import com.projectlab.core.domain.use_cases.users.UsersUseCases
import com.projectlab.core.domain.util.Result
import com.projectlab.core.presentation.designsystem.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val hotelsUseCases: HotelsUseCases,
    private val usersUseCases: UsersUseCases,
    private val userSessionProvider: UserSessionProvider,
    private val getCoordinatesFromCityUseCase: GetCoordinatesFromCityUseCase

) : ViewModel() {

    private val _uiStateHotelSearch = MutableStateFlow(SearchHotelState())
    val uiStateHotelSearch: StateFlow<SearchHotelState> = _uiStateHotelSearch.asStateFlow()

    private val _uiStateHotelDetails = MutableStateFlow(DetailHotelState())
    val uiStateHotelDetails: StateFlow<DetailHotelState> = _uiStateHotelDetails.asStateFlow()

    private val _uiStateBookingHotel = MutableStateFlow(BookingHotelState())
    val uiStateBookingHotel: StateFlow<BookingHotelState> = _uiStateBookingHotel.asStateFlow()

    private var user by mutableStateOf(
        User()
    )
        private set

    init {
        getUserById()
    }

    fun getUserById() = viewModelScope.launch {
        val userId = userSessionProvider.getUserSessionId()
        usersUseCases.getUserById(userId.toString()).collect() { userDB ->
            user = userDB
            _uiStateBookingHotel.update {
                it.copy(
                    guestName = "${userDB.firstName} ${userDB.lastName}",
                    guestNumber = userDB.phoneNumber,
                    countryCode = userDB.countryCode,
                    email = userDB.email,
                )
            }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _uiStateHotelSearch.update { it.copy(query = newQuery) }
    }

    fun onSearchSubmittedWithCoordinates() {

        viewModelScope.launch {
            _uiStateHotelSearch.update { it.copy(isLoading = true) }
            val locationData = getCoordinatesFromCityUseCase(uiStateHotelSearch.value.query)
            try {
                if (locationData != null) {
                    when (val result = hotelsUseCases.getHotelsByCoordinates(
                        locationData.first,
                        locationData.second,
                        emptyList(),
                        emptyList()
                    )) {
                        is Result.Success -> {
                            _uiStateHotelSearch.update { it.copy(hotels = result.data.toMutableList()) }
                        }

                        is Result.Error -> {
                            _uiStateHotelSearch.update { it.copy(error = "Unknown error") }
                        }

                    }
                } else {
                    _uiStateHotelSearch.update {
                        it.copy(error = "Could not find coordinates for the specified city")
                    }
                }
            } catch (e: Exception) {
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

    fun confirmHotelBooking(
        context: Context,
        checkIn: LocalDate?,
        checkOut: LocalDate?,
        onSuccessBooking: () -> Unit
    ) {


        if (checkIn == null || checkOut == null) {
            Toast.makeText(
                context,
                StringResource(R.string.error_dates).asString(context),
                Toast.LENGTH_LONG,
            ).show()
        } else {
            onSuccessBooking()
        }

    }

    fun showAllResults() {
        _uiStateHotelSearch.update { it.copy(showAllResults = true) }
    }

    fun getHotelDetails(hotelId: String) {
        val hotelFound = _uiStateHotelSearch.value.hotels.find { it.id == hotelId }
        _uiStateHotelDetails.update { it.copy(hotelUi = hotelFound!!.toHotelUi()) }
        _uiStateBookingHotel.update { it.copy(hotelUi = hotelFound!!.toHotelUi()) }
    }

    fun favoriteHotel(hotel: Hotel) {
        viewModelScope.launch {
            try {
                when (hotelsUseCases.favoriteHotel(user.id, hotel)) {
                    is Result.Success -> {

                        val updatedHotels = _uiStateHotelSearch.value.hotels.map {
                            if (it == hotel) {
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
                when (hotelsUseCases.unfavoriteHotel(hotelId)) {
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