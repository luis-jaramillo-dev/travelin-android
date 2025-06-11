package com.projectlab.booking.presentation.itinerary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items as lazyItems
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CircularProgressIndicator
import com.projectlab.core.presentation.designsystem.component.BackIconButton
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.component.IconMore
import com.projectlab.core.presentation.designsystem.component.TravelinIconButton
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun ItinerariesScreen(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit,
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
                uiState = ItinerariesUiState(),
                onClickBack = onClickBack,
            )
        }
    )
}

@Composable
fun ItinerariesScreenComponent(
    modifier: Modifier = Modifier,
    uiState: ItinerariesUiState,
    onClickBack: () -> Unit,
) {
    val itineraries = uiState.itineraries

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
                    onClick = { onClickBack() },
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

            Column (
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 16.dp, top = 100.dp)
                    .fillMaxWidth(),
            ) {
                Row (
                    modifier = Modifier
                        .padding(),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 1.dp, end = 230.dp),
                        text = "Add Itinerary",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    TravelinIconButton(
                        modifier = Modifier,
                        onClick = {},// TODO: Implement add itinerary action
                        icon = {
                            IconMore(
                                modifier = Modifier,
                            )
                        }
                    )
                }
            }
            // Spacer to add space between the title and the itineraries list
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.searchSpacer))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 160.dp),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ItineraryCard(
                    modifier = Modifier,
                    onClick = {}
                )
            }


//            if (uiState.isLoading) {
//                CircularProgressIndicator()
//            } else if (uiState.error != null) {
//                Text("Error: ${uiState.error}")
//            } else {
//                LazyColumn (
//                    modifier = Modifier.fillMaxSize(),
//                    contentPadding = PaddingValues(vertical = 8.dp)
//                ){
//                    lazyItems(uiState.itineraries, key = {it.id}) { itinerary ->
//                        ItineraryCard(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 16.dp, vertical = 8.dp),
//                            onClick = {}
//                        )
//                    }
//                }
//            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ItinerariesScreenPreview() {
    ItinerariesScreen(
        onClickBack = {},
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
        onClickBack = {},
        uiState = ItinerariesUiState(),
        modifier = Modifier.fillMaxWidth()
    )
}