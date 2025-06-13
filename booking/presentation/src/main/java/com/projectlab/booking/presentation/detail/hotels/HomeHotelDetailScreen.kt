package com.projectlab.booking.presentation.detail.hotels

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.screens.hotels.details.components.DetailHotelBottomBar
import com.projectlab.booking.presentation.screens.hotels.details.components.DetailHotelContent
import com.projectlab.core.presentation.designsystem.component.HeaderWithBack

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeHotelDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeHotelDetailViewModel,
    onClickBack: () -> Unit,
    onClickBookingHotel: () -> Unit
) {
    val hotel = viewModel.selectedHotel.collectAsState().value

       Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { HeaderWithBack(onClickBack = onClickBack) },
            content = {
                hotel?.let { hotelUi ->
                    DetailHotelContent(
                        modifier = modifier.padding(it),
                        hotelUi = hotelUi
                    )
                }
            },
            bottomBar = {
                hotel?.let {
                    DetailHotelBottomBar(
                        hotelUi = it,
                        onClickBookingHotel = { onClickBookingHotel() }
                    )
                }
            }
        )
    }
