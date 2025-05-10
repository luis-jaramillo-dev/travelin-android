package com.projectlab.core.presentation.ui.components

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.projectlab.core.presentation.ui.model.LocationPermissionState
import com.projectlab.core.presentation.ui.utils.LocationUtils
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel

@Composable
fun LocationPermissionComponent(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }
    val permissionState = remember { mutableStateOf(LocationPermissionState.NOT_REQUESTED) }

    val location = viewModel.location.value
    val address = viewModel.address.value

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (fineGranted && coarseGranted) {
            locationUtils.requestLocationUpdates(viewModel)
        } else {
            permissionState.value = LocationPermissionState.DENIED
        }
        showDialog = false
    }

    if (showDialog) {
        CenterLocationDialog(
            address = address,
            onGetLocation = {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            },
            onReject = {
                permissionState.value = LocationPermissionState.DENIED
                showDialog = false
            },
            viewModel = viewModel
        )
    }

    if (!showDialog) {
        BottomLocationBar(
            location = location,
            onGetLocation = {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        )
    }
}