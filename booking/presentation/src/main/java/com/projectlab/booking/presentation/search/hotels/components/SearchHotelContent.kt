package com.projectlab.booking.presentation.screens.hotels.search.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.projectlab.booking.models.toHotelUi
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.SearchHotelState
import com.projectlab.booking.presentation.search.hotels.components.SearchHotelListItem
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun SearchHotelContent(
    modifier: Modifier = Modifier,
    uiState: SearchHotelState,
    viewModel: HotelsViewModel,
    navController: NavHostController
) {
    SearchHotelsResultsComponent(
        uiState = uiState,
        navController = navController,
        onShowAllResults = { viewModel.showAllResults() },
        viewModel = viewModel
    )
}

@Composable
fun SearchHotelsResultsComponent(
    modifier: Modifier = Modifier,
    uiState: SearchHotelState,
    onShowAllResults: () -> Unit,
    navController: NavHostController,
    viewModel: HotelsViewModel
) {
    val hotels = uiState.hotels
    val showAll = uiState.showAllResults

    if (hotels.isNotEmpty()) {
        val itemsToShow = if (showAll) hotels else hotels.take(3)
        val restSize = hotels.size - 3

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(itemsToShow) {

                SearchHotelListItem(
                    modifier = Modifier.fillMaxWidth(),
                    hotelUi = it.toHotelUi(),
                    onClickDetail = { navController.navigate("hotelDetail/${it.id}") },
                    onClickFavorite = { viewModel.unfavoriteHotel(it.id) },
                    onClickUnfavorite = { viewModel.favoriteHotel(it.id) },
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (!showAll && restSize > 0) {
                item {
                    ButtonComponent(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.small)
                            .fillMaxWidth(),
                        text = stringResource(R.string.show_more_available, restSize),
                        onClick = onShowAllResults,
                        variant = ButtonVariant.Outline,
                    )
                }
            }
        }
    }
}