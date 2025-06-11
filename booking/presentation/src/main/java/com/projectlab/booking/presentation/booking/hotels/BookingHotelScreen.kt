package com.projectlab.booking.presentation.booking.hotels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.projectlab.booking.presentation.booking.hotels.components.BookingHotelContent
import com.projectlab.booking.presentation.booking.hotels.components.BookingHotelFooter
import com.projectlab.booking.presentation.HotelsViewModel
import com.projectlab.core.presentation.designsystem.component.HeaderWithBack
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingHotelScreen(
    viewModel: HotelsViewModel,
    onSuccessBooking: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val state by viewModel.uiStateBookingHotel.collectAsState()
    val context = LocalContext.current
    val checkIn: MutableState<LocalDate?> = remember { mutableStateOf(state.checkIn) }
    val checkOut: MutableState<LocalDate?> = remember { mutableStateOf(state.checkOut) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HeaderWithBack(
                title = "Booking details",
                onClickBack = { onClickBack() })
        },
        bottomBar = {
            BookingHotelFooter(
                hotelUi = state.hotelUi!!,
                confirmBooking = {
                    viewModel.confirmHotelBooking(
                        context,
                        checkIn.value,
                        checkOut.value,
                        onSuccessBooking
                    )
                }
            )
        },
        content = {
            BookingHotelContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                state = state,
                checkIn = checkIn,
                checkOut = checkOut
            )
        }
    )
}