package com.projectlab.booking.presentation.search.hotels

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.projectlab.booking.presentation.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.components.SearchHotelContent
import com.projectlab.booking.presentation.screens.hotels.search.components.SearchHotelHeader
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun SearchHotelScreen(
    modifier: Modifier = Modifier,
    viewModel: HotelsViewModel,
    navController: NavHostController,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiStateHotelSearch.collectAsState()

    Scaffold(
        topBar = {
            SearchHotelHeader(
                uiState = uiState,
                viewModel = viewModel,
                onBackPressed = onBackClick
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = MaterialTheme.spacing.SectionSpacing),
            ) {
                if (uiState.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    SearchHotelContent(
                        viewModel = viewModel,
                        navController = navController,
                        uiState = uiState
                    )
                }
            }
        }
    )
}