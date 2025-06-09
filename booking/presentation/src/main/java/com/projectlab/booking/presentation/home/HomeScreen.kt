package com.projectlab.booking.presentation.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.projectlab.booking.presentation.favorites.FavoritesViewModel
import com.projectlab.booking.presentation.home.components.HomeSearchComponent
import com.projectlab.booking.presentation.home.components.RecommendedActivitiesComponent
import com.projectlab.booking.presentation.home.components.RecommendedHotelsComponent
import com.projectlab.core.data.mapper.toFavoriteActivityEntity
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    locationViewModel: LocationViewModel,
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavController,
    onFavoritesClick: () -> Unit,
    onItinsClick: () -> Unit,
    onProfileClick: () -> Unit,
    onClickSearchHotel: () -> Unit,
    onItemClick: (String) -> Unit,
) {
    val context = LocalContext.current
    val currentLocation = locationViewModel.location.value
    val uiState by homeViewModel.uiState.collectAsState()

    val favoriteIds = favoritesViewModel.favoriteActivityIds.collectAsState().value

    val recommendedActivities = uiState.recommendedActivities.map { activity ->
        activity to favoriteIds.contains(activity.id)
    }

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
        snapshotFlow { locationViewModel.location.value }
            .filterNotNull()
            .firstOrNull()
            ?.let { location ->
                homeViewModel.fetchRecommendedActivities(location)
            } ?: run {
            homeViewModel.fetchRecommendedActivities(null)
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

    LaunchedEffect(Unit) {
        favoritesViewModel.queryFavoriteActivities()
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                BottomNavRoute.HOME,
                onHomeClick = {},
                onFavoritesClick,
                onItinsClick,
                onProfileClick,
            )
        },
        content =
            { padding ->
                HomeScreenComponent(
                    contentPadding = padding,
                    uiState = uiState,
                    onQueryChange = homeViewModel::onQueryChange,
                    onQuerySubmitted = {
                        homeViewModel.onSearchPressed()
                        homeViewModel.onSearchSubmitted()
                    },
                    onDeleteHistoryEntry = homeViewModel::onDeleteHistoryEntry,
                    onClickSearchHotel = { onClickSearchHotel() },
                    location = locationViewModel.address.value,
                    favoriteIds = favoriteIds,
                    onActivityClick = onItemClick,
                    onFavoritesToggle = { activity ->
                        favoritesViewModel.toggleFavorite(activity.toFavoriteActivityEntity())
                    }
                )
            }
    )
}


@Composable
fun HomeScreenComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    location: String,
    favoriteIds: List<String>,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.none),
    onQueryChange: (String) -> Unit,
    onQuerySubmitted: () -> Unit,
    onDeleteHistoryEntry: (String) -> Unit,
    onClickSearchHotel: () -> Unit,
    onActivityClick: (id: String) -> Unit,
    onFavoritesToggle: (ActivityDto) -> Unit
) {

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(contentPadding)
            .navigationBarsPadding(),
    ) {
        HomeSearchComponent(
            uiState = uiState,
            onQueryChange = onQueryChange,
            onQuerySubmitted = onQuerySubmitted,
            onDeleteHistoryEntry = onDeleteHistoryEntry,
            onClickSearchHotel = { onClickSearchHotel() }
        )
        Spacer(modifier.height(MaterialTheme.spacing.semiHuge))
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Column {

                    RecommendedActivitiesComponent(
                        uiState = uiState,
                        location = location,
                        favoriteIds = favoriteIds,
                        onFavoriteClick = onFavoritesToggle,
                        onItemClick = onActivityClick
                    )

                    Spacer(modifier.height(MaterialTheme.spacing.semiHuge))
                    RecommendedHotelsComponent(
                        uiState = uiState,
                        location = location,
                        favoriteIds = favoriteIds,
                        onFavoriteClick = onFavoritesToggle,
                        onItemClick = onActivityClick
                    )
                }
            }
        }
    }
}




