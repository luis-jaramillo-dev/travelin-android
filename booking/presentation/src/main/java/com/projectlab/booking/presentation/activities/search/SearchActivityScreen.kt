package com.projectlab.booking.presentation.activities.search

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.IconBack
import com.projectlab.core.presentation.designsystem.component.SearchPlaces
import com.projectlab.core.presentation.designsystem.component.IconLocation
import com.projectlab.core.presentation.designsystem.component.TourListCard
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import com.projectlab.core.domain.model.Location
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.component.SearchBarComponent
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.utils.LocationUtils

@Composable
fun SearchActivityScreen(
    modifier: Modifier = Modifier,
    locationViewModel: LocationViewModel,
    searchActivityViewModel: SearchActivityViewModel,
    locationUtils: LocationUtils
) {
    val context = LocalContext.current
    val address by locationViewModel.address
    val currentLocation = locationViewModel.location.value
    val uiState by searchActivityViewModel.uiState.collectAsState()

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
        if (!locationUtils.hasLocationPermission(context)) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationViewModel.getCurrentLocation()
        }
    }

    val onEnter = {
        Log.d("SearchActivityViewModel", "Enter pressed")
        if (uiState.query.isNotBlank()) {
            searchActivityViewModel.onSearchSubmitted()
        }
    }

    val onSearchNearbyClick: () -> Unit = {
        if (!locationUtils.hasLocationPermission(context)) {
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
            IconBack(modifier = Modifier, size = 40)
            SearchBarComponent(
                query = uiState.query,
                onEnter = onEnter,
                modifier = Modifier,
                onQueryChange = { searchActivityViewModel.onQueryChanged(it) }
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))
        if (uiState.activities.isEmpty()) {
            SearchPlaces(
                modifier = modifier.padding(6.dp),
                locationIcon = { modifier -> IconLocation(modifier) },
                searchString = stringResource(R.string.search_global_nearby),
                location = address,
                onClick = onSearchNearbyClick
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        SearchActivityResultsComponent(
            uiState = uiState,
            onShowAllResults = { searchActivityViewModel.showAllResults() }
        )
    }
}



@Composable
fun SearchActivityResultsComponent(
    uiState: SearchActivityUiState,
    onShowAllResults: () -> Unit
) {
    val activities = uiState.activities
    val showAll = uiState.showAllResults

    val context = LocalContext.current
    val locationUtils = remember { LocationUtils(context) }

    val cityMap = remember { mutableStateMapOf<String, String?>() }

    LaunchedEffect(activities) {
        activities.forEach { activity ->
            if (!cityMap.containsKey(activity.id)) {
                val location = Location(
                    latitude = activity.geoCode.latitude,
                    longitude = activity.geoCode.longitude
                )
                val city = locationUtils.reverseGeocodeLocation(location)
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

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(itemsToShow) { activity ->
                val city = cityMap[activity.id]

                TourListCard(
                    activity = activity,
                    modifier = Modifier.fillMaxWidth(),
                    city = city
                )

                Spacer(modifier = Modifier.height(16.dp))
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