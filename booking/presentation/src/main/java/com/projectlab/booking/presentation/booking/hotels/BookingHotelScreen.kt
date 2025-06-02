package com.projectlab.booking.presentation.booking.hotels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.booking.hotels.components.BookingHotelContent
import com.projectlab.booking.presentation.booking.hotels.components.BookingHotelFooter
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.core.presentation.designsystem.component.HeaderWithBack

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingHotelScreen(
    modifier: Modifier = Modifier,
    onSuccessBooking: () -> Unit,
    onClickBack: () -> Unit,
    viewModel: HotelsViewModel
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderWithBack(
                title = "Booking details",
                onClickBack = { onClickBack() })
        },
        bottomBar = {
            BookingHotelFooter(price = viewModel.bookingState.currentHotel!!.hotelOffers!![0].price)
        },
        content = {
            BookingHotelContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                state = viewModel.bookingState
            )
        }
    )

}