package com.projectlab.booking.presentation.itinerary

import android.R.attr.left
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                current = BottomNavRoute.TRIPS,
                onHomeClick = onHomeClick,
                onFavoritesClick = onFavoritesClick,
                onItinsClick = onItinsClick,
                onProfileClick = onProfileClick,
            )
        },
        content = { innerPadding ->
            ItinerariesScreenComponent(
                modifier = Modifier,
                navController = navController
            )
        }
    )
}

@Composable
fun ItinerariesScreenComponent(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BackIconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(MaterialTheme.spacing.extraLarge2)
                        .padding(MaterialTheme.spacing.extraSmall)
                )
            }
            Row(
                modifier = modifier
                    .statusBarsPadding()
                    .padding(start = 16.dp, top = 60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.Spacer),
                    text = "Itineraries",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row (
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 16.dp, top = 100.dp)
                    .fillMaxWidth(),
            ) {
                Column (
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.Spacer),
                        text = "Add Itinerary",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    )
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

@Preview(showBackground = true)
@Composable
fun ItinerariesScreenComponentPreview() {
    ItinerariesScreenComponent(
        navController = NavController(LocalContext.current)
    )
}