package com.projectlab.booking.presentation.search.activities

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.projectlab.core.domain.model.Location
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.BackIconButton
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.component.IconLocation
import com.projectlab.core.presentation.designsystem.component.SearchBar
import com.projectlab.core.presentation.designsystem.component.SearchPlaces
import com.projectlab.core.presentation.designsystem.component.TourListCard
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel

/**
 *  This is the main screen for searching activities.
 *  It includes a search bar and a list of activities.
 *  It also handles location permissions and
 *  retrieves the user's current location.
 *
 */

@Composable
fun SearchActivityScreen(
    modifier: Modifier = Modifier,
    locationViewModel: LocationViewModel,
    searchActivityViewModel: SearchActivityViewModel,
    navController: NavController,
    onActivityClick: (String) -> Unit,
) {
    val context = LocalContext.current
    val address by locationViewModel.address
    val currentLocation = locationViewModel.location.value
    val uiState by searchActivityViewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val initialQuery = backStackEntry
        ?.arguments
        ?.getString("query")
        .orEmpty()

    // As soon as the Composable is mounted, start the search
    LaunchedEffect(initialQuery) {
        if (initialQuery.isNotBlank()) {
            searchActivityViewModel.searchWithInitialQuery(initialQuery)
        }
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
    }

    /**
     * @param onEnter: Callback function to handle the Enter key press event.
     */
    val onEnter = {
        if (uiState.query.isNotBlank()) {
            searchActivityViewModel.onSearchSubmitted()
        }
    }

    /**
     * @param onSearchNearbyClick: Callback function to handle the search nearby button click event.
     */

    val onSearchNearbyClick: () -> Unit = {
        if (!locationViewModel.hasLocationPermission()) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationViewModel.getCurrentLocation()
            currentLocation?.let {
                searchActivityViewModel.searchByCurrentLocation(it)
            }
        }
    }

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(MaterialTheme.spacing.SectionSpacing)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BackIconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(MaterialTheme.spacing.extraLarge2)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            SearchBar(
                query = uiState.query,
                contentsDescription = "Search City Input",
                placeholder = stringResource(R.string.search_city_placeholder),
                onEnter = onEnter,
                onQueryChange = { searchActivityViewModel.onQueryChanged(it) },
                onSearchPressed = onEnter,
                history = uiState.history,
                onDeleteHistoryEntry = { value ->
                    searchActivityViewModel.onDeleteHistoryEntry(value)
                }
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

        SearchPlaces(
            modifier = modifier.padding(MaterialTheme.spacing.searchPlacesPadding),
            locationIcon = { modifier -> IconLocation(modifier) },
            searchString = stringResource(R.string.search_global_nearby),
            location = address,
            onClick = onSearchNearbyClick
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        SearchActivityResultsComponent(
            uiState = uiState,
            onShowAllResults = { searchActivityViewModel.showAllResults() },
            navController = navController,
            reverseGeocode = { location -> locationViewModel.reverseGeocodeLocation(location) }
        )
    }
}

@Composable
fun SearchActivityResultsComponent(
    uiState: SearchActivityUiState,
    onShowAllResults: () -> Unit,
    navController: NavController,
    reverseGeocode: suspend (Location) -> String
) {
    val activities = uiState.activities
    val showAll = uiState.showAllResults

    val cityMap = remember { mutableStateMapOf<String, String?>() }

    LaunchedEffect(activities) {
        activities.forEach { activity ->
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

    if (activities.isNotEmpty()) {
        val itemsToShow = if (showAll) activities else activities.take(3)
        val restSize = activities.size - 3

        Text(
            text = stringResource(R.string.searching_results),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall)
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.searchSpacer))

        LazyColumn {
            items(itemsToShow) { activity ->
                val city = cityMap[activity.id]

                TourListCard(
                    activity = activity,
                    modifier = Modifier.fillMaxWidth(),
                    city = city,
                    onPress = {
                        navController.navigate("activityDetail/${activity.id}")
                    }
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
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