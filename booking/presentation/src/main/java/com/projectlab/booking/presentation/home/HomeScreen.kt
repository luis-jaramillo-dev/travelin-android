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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.projectlab.booking.models.HotelUi
import com.projectlab.booking.presentation.home.components.HomeSearchComponent
import com.projectlab.booking.presentation.home.components.RecommendedActivitiesComponent
import com.projectlab.booking.presentation.home.components.RecommendedHotelsComponent
import com.projectlab.core.data.mapper.toFavoriteActivityEntity
import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.model.Hotel
import com.projectlab.core.domain.model.Location
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import kotlin.collections.set

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
    onActivityItemClick: (String) -> Unit,
    onHotelItemClick: (HotelUi) -> Unit,
) {
    val context = LocalContext.current
    val currentLocation = locationViewModel.location.value
    val uiState by homeViewModel.uiState.collectAsState()
    val favoriteActivityIds = homeViewModel.favoriteActivityIds.collectAsState().value
    val favoriteHotelIds = homeViewModel.favoriteHotelsIds.collectAsState().value

    var location by remember { mutableStateOf("") }

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

    LaunchedEffect(currentLocation) {
        currentLocation?.let { location = locationViewModel.reverseGeocodeLocation(it) }
    }

    LaunchedEffect(currentLocation?.latitude, currentLocation?.longitude) {
        if (currentLocation != null) {
            homeViewModel.fetchRecommendedActivities(currentLocation)
        }
    }

    LaunchedEffect(currentLocation?.latitude, currentLocation?.longitude) {
        if (currentLocation != null) {
        homeViewModel.fetchRecommendedHotels(currentLocation)
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
        homeViewModel.fetchFavoriteActivities()
    }

    LaunchedEffect(Unit) {
        homeViewModel.fetchFavoriteHotels()
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
                    contentPadding = padding,
                    uiState = uiState,
                    onQueryChange = homeViewModel::onQueryChange,
                    onQuerySubmitted = {
                        homeViewModel.onSearchPressed()
                        homeViewModel.onSearchSubmitted()
                    },
                    onDeleteHistoryEntry = homeViewModel::onDeleteHistoryEntry,
                    onClickSearchHotel = { onClickSearchHotel() },
                    location = location,
                    favoriteActivityIds = favoriteActivityIds,
                    favoriteHotelIds = favoriteHotelIds,
                    onActivityClick = onActivityItemClick,
                    onHotelClick = onHotelItemClick,
                    onActivityFavoritesToggle = { activity ->
                        homeViewModel.toggleFavoriteActivity(activity.toFavoriteActivityEntity())
                    },
                    onHotelFavoritesToggle = { hotel ->
                        homeViewModel.toggleFavoriteHotel(hotel)
                    },
                    reverseGeocode = { location -> locationViewModel.reverseGeocodeLocation(location) }
                )
            }
    )
}


@Composable
fun HomeScreenComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    location: String,
    favoriteActivityIds: List<String>,
    favoriteHotelIds: List<String>,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.none),
    onQueryChange: (String) -> Unit,
    onQuerySubmitted: () -> Unit,
    onDeleteHistoryEntry: (String) -> Unit,
    onClickSearchHotel: () -> Unit,
    onActivityClick: (id: String) -> Unit,
    onHotelClick: (HotelUi) -> Unit,
    onActivityFavoritesToggle: (ActivityDto) -> Unit,
    onHotelFavoritesToggle: (Hotel) -> Unit,
    reverseGeocode: suspend (Location) -> String,
) {

    val recommendedActivities = uiState.recommendedActivities
    val cityMap = remember { mutableStateMapOf<String, String?>() }

    LaunchedEffect(recommendedActivities) {
        recommendedActivities.forEach { activity ->
            if (!cityMap.containsKey(activity.id)) {
                val location = Location(
                    latitude = activity.geoCode.latitude,
                    longitude = activity.geoCode.longitude
                )
                val city = reverseGeocode(location)
                cityMap[activity.id] = city
            }
        }
    }


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
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Column {

                    RecommendedActivitiesComponent(
                        uiState = uiState,
                        cityMap = cityMap,
                        favoriteIds = favoriteActivityIds,
                        onFavoriteClick = onActivityFavoritesToggle,
                        onItemClick = onActivityClick
                    )

                    Spacer(modifier.height(MaterialTheme.spacing.semiHuge))
                    RecommendedHotelsComponent(
                        uiState = uiState,
                        favoriteIds = favoriteHotelIds,
                        onFavoriteClick = onHotelFavoritesToggle,
                        onItemClick = onHotelClick
                    )
                }
            }
        }
    }
}




