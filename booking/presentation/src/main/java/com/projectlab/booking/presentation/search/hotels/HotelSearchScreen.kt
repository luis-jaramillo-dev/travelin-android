package com.projectlab.booking.presentation.screens.hotels.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.components.HotelSearchContent
import com.projectlab.booking.presentation.screens.hotels.search.components.HotelSearchHeader
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HotelSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: HotelsViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.uiStateHotelSearch.collectAsState()


    Scaffold(
        topBar = { HotelSearchHeader(uiState = uiState, viewModel = viewModel) },
        content = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = MaterialTheme.spacing.SectionSpacing),
            ) {
                HotelSearchContent(
                    viewModel = viewModel,
                    navController = navController,
                    uiState = uiState
                )
            }

        }
    )
}