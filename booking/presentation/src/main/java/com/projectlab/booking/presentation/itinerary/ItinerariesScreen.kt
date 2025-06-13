package com.projectlab.booking.presentation.itinerary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.projectlab.core.domain.entity.ItineraryEntity
import com.projectlab.core.presentation.designsystem.component.BackIconButton
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.component.IconMore
import com.projectlab.core.presentation.designsystem.component.TravelinIconButton
import com.projectlab.core.presentation.designsystem.theme.spacing
import androidx.compose.runtime.getValue


@Composable
fun ItinerariesScreen(
    modifier: Modifier = Modifier,
    viewModel: ItinerariesViewModel,
    onClickBack: () -> Unit,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onItinsClick: () -> Unit,
    onProfileClick: () -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.loadItineraries()
    }

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
            val uiState by viewModel.uiState.collectAsState()
            ItinerariesScreenComponent(
                modifier = Modifier.padding(innerPadding),
                uiState = uiState,
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {


    }

    // Back button at the top of the screen
    Row(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.extraLarge2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackIconButton(
            onClick = { onClickBack() },
            modifier = Modifier
                .size(MaterialTheme.spacing.extraLarge2)
        )
    }
    // Title of the screen
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(start = 16.dp, top = 60.dp)
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier,
            text = "Itineraries",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }

    // Add Itinerary and button at the top of the screen
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(start = 16.dp, top = 105.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(),
        ) {
            Text(
                modifier = Modifier.padding(start = 1.dp, top = 10.dp, end = 230.dp),
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

    Box (
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {

        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }
            uiState.error != null -> {
                Text("Error: ${uiState.error}")
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 240.dp),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                ) {
                    items(
                        itineraries,
                       // key = {it.id}
                    ) { itinerary ->
                        ItineraryCard(
                            modifier = Modifier
                                .padding(top = 5.dp),
                            itinerary = itinerary,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ItinerariesScreenPreview() {
//    ItinerariesScreen(
//        modifier = Modifier,
//        viewModel = ItinerariesViewModel(),
//        onClickBack = {},
//        onHomeClick = {},
//        onFavoritesClick = {},
//        onItinsClick = {},
//        onProfileClick = {}
//    )
//}

// QWEasd123

@Preview(showBackground = true)
@Composable
fun ItinerariesScreenComponentPreview() {
    ItinerariesScreenComponent(
        modifier = Modifier,
        uiState = ItinerariesUiState(
            itineraries = listOf(
                ItineraryEntity(
                    id = "1",
                    title = "Sample Itinerary",
                    startDate = java.time.Instant.parse("2025-07-01T00:00:00Z"),
                    endDate = java.time.Instant.parse("2025-07-20T00:00:00Z"),
                    totalItineraryPrice = 1000.0
                ),
                ItineraryEntity(
                    id = "2",
                    title = "Another Itinerary",
                    startDate = java.time.Instant.parse("2025-08-01T00:00:00Z"),
                    endDate = java.time.Instant.parse("2025-08-15T00:00:00Z"),
                    totalItineraryPrice = 1500.0
                )
            ),
            isLoading = false,
            error = null
        ),
        onClickBack = {},
    )
}