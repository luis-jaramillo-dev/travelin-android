package com.projectlab.booking.presentation.screens.hotels.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.search.HotelSearchUiState
import com.projectlab.core.presentation.designsystem.component.IconBack
import com.projectlab.core.presentation.designsystem.component.SearchBarComponent
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun HotelSearchHeader(
    modifier: Modifier = Modifier,
    uiState: HotelSearchUiState,
    viewModel: HotelsViewModel
) {

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(MaterialTheme.spacing.SectionSpacing)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBack(modifier = Modifier, size = 40)
            SearchBarComponent(
                query = uiState.query,
                onEnter = {
                    if (uiState.query.isNotBlank()) {
                        viewModel.onSearchSubmitted()
                    }
                },
                modifier = Modifier,
                onQueryChange = { viewModel.onQueryChanged(it) },
                onSearchPressed = {}
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

    }
}