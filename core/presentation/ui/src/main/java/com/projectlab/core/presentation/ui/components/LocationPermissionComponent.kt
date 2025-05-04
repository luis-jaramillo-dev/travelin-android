package com.projectlab.core.presentation.ui.components

import android.Manifest
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import com.projectlab.core.presentation.ui.R
import com.projectlab.core.presentation.ui.model.LocationPermissionState
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel

@Composable
fun LocationPermissionComponent(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    context: Context,
    showBottomBar: MutableState<Boolean>,
) {
    val permissionState = remember { mutableStateOf(LocationPermissionState.NOT_REQUESTED) }
    val location = viewModel.location.value

    LaunchedEffect(location) {
        if (location != null && viewModel.address.value.isNullOrBlank()) {
            val address = locationUtils.reverseGeocodeLocation(location)
            viewModel.updateAddress(address)
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            locationUtils.requestLocationUpdates(viewModel = viewModel)
        } else {
            val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            permissionState.value = LocationPermissionState.DENIED

            if (rationaleRequired) {
                Toast.makeText(
                    context,
                    context.getString(R.string.location_permission_required),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.permission_denied_enable),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    if (showBottomBar.value) {
        BottomLocationBar(
            location = viewModel.location.value,
            address = viewModel.address.value,
            onGetLocation = {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }

        )
    } else {
        CenterLocationPrompt(
            address = viewModel.address.value,
            onGetLocation = {
                showBottomBar.value = true
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            },
            onReject = {
                permissionState.value = LocationPermissionState.DENIED
                showBottomBar.value = true
            },
            viewModel = viewModel,

        )
    }
}
