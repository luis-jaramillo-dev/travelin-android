package com.projectlab.booking.presentation.detail.hotels

import androidx.lifecycle.ViewModel
import com.projectlab.booking.models.HotelUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

    @HiltViewModel
    class HomeHotelDetailViewModel @Inject constructor(
    ) : ViewModel() {

        private val _selectedHotel = MutableStateFlow<HotelUi?>(null)
        val selectedHotel: StateFlow<HotelUi?> = _selectedHotel

        fun selectHotel(hotel: HotelUi) {
            _selectedHotel.value = hotel
        }
    }