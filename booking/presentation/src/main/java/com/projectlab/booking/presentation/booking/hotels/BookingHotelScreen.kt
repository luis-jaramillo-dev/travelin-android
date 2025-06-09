package com.projectlab.booking.presentation.booking.hotels

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.booking.hotels.components.BookingHotelContent
import com.projectlab.booking.presentation.screens.HotelsViewModel

@Composable
fun BookingHotelScreen(
    viewModel: HotelsViewModel,
    onSuccessBooking: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold {
        BookingHotelContent(
            modifier = modifier.padding(it)
        )
    }
}