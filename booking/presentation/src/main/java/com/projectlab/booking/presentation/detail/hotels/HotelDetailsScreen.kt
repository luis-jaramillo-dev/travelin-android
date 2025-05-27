package com.projectlab.booking.presentation.screens.hotels.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.details.components.HotelDetailsBottomBar
import com.projectlab.booking.presentation.screens.hotels.details.components.HotelDetailsContent
import com.projectlab.booking.presentation.screens.hotels.details.components.HotelDetailsHeader

@Composable
fun HotelDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: HotelsViewModel,
    navController: NavHostController,
    hotelId: String,
) {
    val state by viewModel.uiStateHotelDetails.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getHotelDetails(hotelId)
    }

    if (state.currentHotel != null) {

        val currentHotel = state.currentHotel!!
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HotelDetailsHeader(
                    onNavigationClick = {},
                    hotel = currentHotel
                )
            },
            content = {
                HotelDetailsContent(
                    paddingValues = it,
                    hotel = currentHotel
                )
            },
            bottomBar = {
                HotelDetailsBottomBar(
                    hotel = currentHotel
                )
            }
        )
    }
}