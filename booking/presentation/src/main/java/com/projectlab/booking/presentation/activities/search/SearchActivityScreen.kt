package com.projectlab.booking.presentation.activities.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.IconBack
import com.projectlab.core.presentation.designsystem.component.SearchPlaces
import com.projectlab.core.presentation.designsystem.component.IconLocation
import com.projectlab.core.presentation.designsystem.component.TourListCard
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.projectlab.core.presentation.ui.components.LocationPermissionComponent
import com.projectlab.core.presentation.ui.di.LocationUtilsEntryPoint
import com.projectlab.core.presentation.ui.utils.LocationUtils
import dagger.hilt.android.EntryPointAccessors


@Composable
fun SearchActivityScreen(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    locationViewModel: LocationViewModel = hiltViewModel(),
    searchActivityViewModel: SearchActivityViewModel = hiltViewModel(),
    locationUtils: LocationUtils
) {
    val address by locationViewModel.address
    val searchQuery by searchActivityViewModel.query.collectAsState()

    val onEnter = {
        Log.d("SearchActivityViewModel", "Enter pressed")
        if (searchQuery.isNotBlank()) {
            searchActivityViewModel.onSearchSubmitted()
        }
    }

    Column(modifier = modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconBack(modifier = Modifier, size = 40)
            SearchBarComponent(
                searchQuery = searchQuery,
                onQueryChanged = {},
                onEnter = onEnter,
                onUseCurrentLocationClicked = {
                    locationViewModel.getCurrentLocation()
                },
                modifier = Modifier,
                viewModel = searchActivityViewModel
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchPlaces(
            modifier = modifier.padding(6.dp),
            locationIcon = { modifier -> IconLocation(modifier) },
            searchString = stringResource(R.string.search_global_nearby),
            location = address
        )
        Spacer(modifier = Modifier.height(40.dp))
        SearchActivityResultsComponent(
            viewModel = searchActivityViewModel
        )
        LocationPermissionComponent(
            locationUtils = locationUtils,
            viewModel = locationViewModel,
        )

    }
}

@Composable
fun SearchBarComponent(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    onEnter: () -> Unit,
    onUseCurrentLocationClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchActivityViewModel,
) {
    val query by viewModel.query.collectAsState()
    val address by viewModel.address.collectAsState()

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                viewModel.onQueryChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "Search City Input" },
            placeholder = { Text(text = stringResource(R.string.search_city_placeholder)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEnter() }
            ),
            singleLine = true
        )

        if (address != null) {
            val nonNullAddress = address!!
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.searching_results_for, nonNullAddress),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun SearchActivityResultsComponent(
    viewModel: SearchActivityViewModel
){
    val activities by viewModel.activities.collectAsState()
    val showAll by viewModel.showAllResults.collectAsState()

    Log.d("SearchActivityResultsComponent", "Activities: $activities")

    Log.d("SearchActivityResults", "Activities count: ${activities.size}")

    if (activities.isNotEmpty()) {
        val firstThree = activities.take(3)
        val rest = activities.drop(3)

        Column {
            firstThree.forEach { activity ->
                TourListCard(activity = activity)
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (!showAll && rest.isNotEmpty()) {
                Button(
                    onClick = { viewModel.showAllResults() },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("Show +${rest.size} more available")
                }
            }

            if (showAll && rest.isNotEmpty()) {
                LazyColumn {
                    items(rest) { activity ->
                        TourListCard(activity = activity)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchActivityScreenWithHilt(onSearchClicked: () -> Unit) {
    val context = LocalContext.current
    val locationViewModel: LocationViewModel = hiltViewModel()

    val locationUtils = remember {
        EntryPointAccessors
            .fromApplication(context.applicationContext, LocationUtilsEntryPoint::class.java)
            .locationUtils()
    }

    SearchActivityScreen(
        onSearchClicked = onSearchClicked,
        locationViewModel = locationViewModel,
        locationUtils = locationUtils
    )
}