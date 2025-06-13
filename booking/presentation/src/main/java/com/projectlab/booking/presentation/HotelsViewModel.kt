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
import com.projectlab.core.domain.entity.HotelEntity
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.User
import com.projectlab.core.domain.repository.UserSessionProvider
import com.projectlab.core.domain.use_cases.hotels.HotelsUseCases
import com.projectlab.core.domain.use_cases.hotels.RemoveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.hotels.SaveFavoriteHotelUseCase
import com.projectlab.core.domain.use_cases.itineraries.ItinerariesUseCases
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
import java.time.ZoneId
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val hotelsUseCases: HotelsUseCases,
    private val usersUseCases: UsersUseCases,
    private val userSessionProvider: UserSessionProvider,
    private val getCoordinatesFromCityUseCase: GetCoordinatesFromCityUseCase,
    private val saveFavoriteHotelUseCase: SaveFavoriteHotelUseCase,
    private val removeFavoriteHotelUseCase: RemoveFavoriteHotelUseCase,
    private val itinerariesUseCases: ItinerariesUseCases


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

    fun saveHotelBooking(startDate: LocalDate, endDate: LocalDate) {
        viewModelScope.launch {
            val checkIn = startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            val checkOut = endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()


            val newItinerary = ItineraryEntity(
                id = LocalDate.now().toString(),
                title = LocalDate.now().toString(),
                startDate = checkIn,
                endDate = checkOut,
                totalItineraryPrice = 15.00
            )

            val newHotelBooking = HotelEntity(
                id = LocalDate.now().toString(),
                hotelName = uiStateBookingHotel.value.hotelUi!!.name,
                hotelRoomNumber = Random.nextInt(from = 1, until = 100),
                hotelPhone = Random.nextInt(from = 1000000, until = 99999999),
                locationRef = null,
                guestName = uiStateBookingHotel.value.guestName,
                guestPhone = Random.nextInt(from = 1000000, until = 99999999),
                idNumber = Random.nextInt(from = 1000000, until = 99999999),
                checkInDate = checkIn,
                checkOutDate = checkOut,
                hotelPrice = uiStateBookingHotel.value.hotelUi!!.price.value.amount
            )

            try {
                val result = itinerariesUseCases.createItinerary(newItinerary)
                var itinId = ""

                if (result.isSuccess) {
                    itinId = result.getOrNull()?.value.toString()

                    val result2 = itinerariesUseCases.addHotelToItinerary(itinId, newHotelBooking)
                    println(result2)
                }
                println(itinId)
            } catch (e: Exception) {
                println(e)
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
            saveHotelBooking(
                checkIn,
                checkOut
            )
            onSuccessBooking()
        }

    }

    fun showAllResults() {
        _uiStateHotelSearch.update { it.copy(isLoading = true) }
        _uiStateHotelSearch.update { it.copy(showAllResults = true) }
        _uiStateHotelSearch.update { it.copy(isLoading = false) }
    }

    fun getHotelDetails(hotelId: String) {
        val hotelFound = _uiStateHotelSearch.value.hotels.find { it.id == hotelId }
        _uiStateHotelDetails.update { it.copy(hotelUi = hotelFound!!.toHotelUi()) }
        _uiStateBookingHotel.update { it.copy(hotelUi = hotelFound!!.toHotelUi()) }
    }

    fun favoriteHotel(hotel: Hotel) {
        viewModelScope.launch {
            try {
                when (saveFavoriteHotelUseCase(hotel)) {
                    is Result.Success -> {
                        val updatedHotels = _uiStateHotelSearch.value.hotels.map {
                            if (it.id == hotel.id) it.copy(isFavourite = true) else it
                        }
                        _uiStateHotelSearch.update { it.copy(hotels = updatedHotels) }
                    }

                    is Result.Error -> {
                        _uiStateHotelSearch.update { it.copy(error = "Error saving favorite") }
                    }
                }
            } catch (e: Exception) {
                _uiStateHotelSearch.update { it.copy(error = e.message ?: "Unknown error") }
            }
        }
    }

    fun unfavoriteHotel(hotelId: String) {
        viewModelScope.launch {
            try {
                when (removeFavoriteHotelUseCase(hotelId)) {
                    is Result.Success -> {
                        val updatedHotels = _uiStateHotelSearch.value.hotels.map {
                            if (it.id == hotelId) it.copy(isFavourite = false) else it
                        }
                        _uiStateHotelSearch.update { it.copy(hotels = updatedHotels) }
                    }

                    is Result.Error -> {
                        _uiStateHotelSearch.update { it.copy(error = "Error removing favorite") }
                    }
                }
            } catch (e: Exception) {
                _uiStateHotelSearch.update { it.copy(error = e.message ?: "Unknown error") }
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