package com.projectlab.booking.presentation.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.projectlab.core.presentation.designsystem.component.ButtonHotel
import com.projectlab.core.presentation.designsystem.component.ButtonOversea
import com.projectlab.core.presentation.designsystem.component.SearchBarComponent
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import com.projectlab.booking.presentation.R as BookingR
import com.projectlab.core.presentation.designsystem.R as DesignSystemR

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    locationViewModel: LocationViewModel,
    homeViewModel: HomeViewModel,
    navController: NavController,
    locationUtils: LocationUtils,
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
        if (!locationUtils.hasLocationPermission(context)) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationViewModel.getCurrentLocation()
        }
    }

    HomeScreenComponent(
        modifier = modifier,
        uiState = uiState,
        navController = navController,
        homeViewModel = homeViewModel,
    )
}

@Composable
fun HomeScreenComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    Column {
        HomeSearchComponent(
            uiState = uiState,
            navController = navController,
            homeViewModel = homeViewModel,
        )
    }
}

@Composable
fun HomeSearchComponent(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    val onQueryChange: (String) -> Unit = { newQuery ->
        homeViewModel.onQueryChange(newQuery)
    }

    val onSearchPressed: () -> Unit = {
        if (uiState.query.isNotBlank()) {
            navController.navigate("search_activities_with_query/${uiState.query}")
        }
    }

    Box(modifier = Modifier.height(MaterialTheme.spacing.homeHeaderImageSize)) {
        Image(
            painter = painterResource(BookingR.drawable.homebackground),
            contentDescription = "Home Background",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth(),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x33000000)),
        )
        Column(modifier = Modifier.padding(MaterialTheme.spacing.semiLarge)) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.homeHeaderSpacer))
            Text(
                text = stringResource(BookingR.string.exploreTheWorld),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontSize = 37.sp,
                fontWeight = FontWeight.W900,
            )
            Text(
                text = stringResource(BookingR.string.travelNextLevel),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontWeight = FontWeight.W600,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            SearchBarComponent(
                query = uiState.query,
                contentsDescription = "Search City Input",
                placeholder = stringResource(DesignSystemR.string.search_city_placeholder),
                onEnter = onSearchPressed,
                onQueryChange = onQueryChange,
                onSearchPressed = onSearchPressed,
                modifier = Modifier.fillMaxWidth(),
                history = listOf(
                    "History 1",
                    "History 2",
                    "History 3",
                    "History 4",
                ),
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                ButtonHotel(
                    modifier = Modifier,
                    onClick = {},
                )
                ButtonOversea(
                    modifier = Modifier,
                    onClick = {},
                )
            }
        }
    }
}