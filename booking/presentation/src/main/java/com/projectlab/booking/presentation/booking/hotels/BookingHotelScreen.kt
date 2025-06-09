package com.projectlab.booking.presentation.booking.hotels

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.booking.hotels.components.BookingHotelContent

@Composable
fun BookingHotelScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        BookingHotelContent(
            modifier = modifier.padding(it)
        )
    }
}