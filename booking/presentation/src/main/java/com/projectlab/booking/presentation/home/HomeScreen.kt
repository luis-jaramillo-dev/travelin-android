package com.projectlab.booking.presentation.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.projectlab.booking.presentation.home.components.HomeSearchComponent
import com.projectlab.booking.presentation.home.components.RecommendedActivitiesComponent
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    locationViewModel: LocationViewModel,
    homeViewModel: HomeViewModel,
    navController: NavController,
    onFavoritesClick: () -> Unit,
    onTripsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onClickSearchHotel: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val context = LocalContext.current
    val currentLocation = locationViewModel.location.value
    val uiState by homeViewModel.uiState.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            locationViewModel.getCurrentLocation()
        } else {
            locationViewModel.setUnknownLocation(context)
        }
    }

    LaunchedEffect(Unit) {
        if (!locationViewModel.hasLocationPermission()) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationViewModel.getCurrentLocation()
        }
    }

    LaunchedEffect(Unit) {
        homeViewModel.navigationEvent.collect { route: String ->
            navController.navigate(route)
        }
    }

    LaunchedEffect(homeViewModel) {
        homeViewModel.fetchSearchHistory()
    }

    LaunchedEffect(currentLocation) {
        homeViewModel.fetchRecommendedActivities(currentLocation)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                BottomNavRoute.HOME,
                onHomeClick = {},
                onFavoritesClick,
                onTripsClick,
                onProfileClick,
            )
        },
        content =
            { padding ->
                HomeScreenComponent(
                    modifier = Modifier.fillMaxSize()
                    .padding(padding),
                    uiState = uiState,
                    onQueryChange = homeViewModel::onQueryChange,
                    onQuerySubmitted = {
                        homeViewModel.onSearchPressed()
                        homeViewModel.onSearchSubmitted()
                    },
                    onDeleteHistoryEntry = homeViewModel::onDeleteHistoryEntry,
                    onClickSearchHotel = { onClickSearchHotel() },
                    location = locationViewModel.address.value,
                    onFavoritesClick = onFavoritesClick,
                    onActivityClick = onItemClick
                )
            }
    )
}


@Composable
fun HomeScreenComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    location: String,
    onQueryChange: (String) -> Unit,
    onQuerySubmitted: () -> Unit,
    onDeleteHistoryEntry: (String) -> Unit,
    onClickSearchHotel: () -> Unit,
    onFavoritesClick: () -> Unit,
    onActivityClick: (id: String) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        HomeSearchComponent(
            uiState = uiState,
            onQueryChange = onQueryChange,
            onQuerySubmitted = onQuerySubmitted,
            onDeleteHistoryEntry = onDeleteHistoryEntry,
            onClickSearchHotel = { onClickSearchHotel() }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regular))

        RecommendedActivitiesComponent(
            modifier = modifier,
            uiState = uiState,
            location = location,
            onFavoriteClick = onFavoritesClick,
            onItemClick = onActivityClick
        )
    }
}




