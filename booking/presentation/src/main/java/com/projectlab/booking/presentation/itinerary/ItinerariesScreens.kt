package com.projectlab.booking.presentation.itinerary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.projectlab.core.presentation.designsystem.component.BackIconButton
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun ItinerariesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                BackIconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(MaterialTheme.spacing.extraLarge2)
                        .padding(MaterialTheme.spacing.extraSmall)
                )
            }
        }
    )
}

@Composable
fun ItinerariesScreenComponent(
    modifier: Modifier = Modifier,
){

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(MaterialTheme.spacing.SectionSpacing)
    ) {

    }

}

@Preview(showBackground = true)
@Composable
fun ItinerariesScreenPreview() {
    ItinerariesScreen(
        navController = NavController(LocalContext.current),
        onHomeClick = {},
        onFavoritesClick = {},
        onItinsClick = {},
        onProfileClick = {}
    )
}