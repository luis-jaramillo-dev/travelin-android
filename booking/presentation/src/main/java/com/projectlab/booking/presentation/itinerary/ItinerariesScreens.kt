package com.projectlab.booking.presentation.itinerary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar

@Composable
fun ItinerariesScreen(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onItinsClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(
                current = BottomNavRoute.HOME,
                onHomeClick = onHomeClick,
                onFavoritesClick = onFavoritesClick,
                onItinsClick = onItinsClick,
                onProfileClick = onProfileClick,
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Itinerarios")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ItinerariesScreenPreview() {
    ItinerariesScreen(
        onHomeClick = {},
        onFavoritesClick = {},
        onItinsClick = {},
        onProfileClick = {}
    )
}