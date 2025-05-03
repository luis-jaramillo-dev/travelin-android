package com.projectlab.core.presentation.ui.components

import android.Manifest
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import com.projectlab.core.presentation.ui.R
import com.projectlab.core.presentation.ui.model.LocationPermissionState
import com.projectlab.core.presentation.ui.utils.LocationUtils

@Composable
fun LocationPermissionComponent(
    locationUtils: LocationUtils,
    context: Context,
    isBottomBar: Boolean = false,
    onPermissionGranted: () -> Unit,
    onPermissionRejected: () -> Unit,
) {
    val permissionState = remember { mutableStateOf(LocationPermissionState.NOT_REQUESTED) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            permissionState.value = LocationPermissionState.GRANTED
            onPermissionGranted()
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
                Toast.makeText(context, context.getString(R.string.location_permission_required), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.permission_denied_enable), Toast.LENGTH_SHORT).show()
            }

            onPermissionRejected()
        }
    }

    if (isBottomBar) {
        BottomLocationBar(
            onGetLocation = {
                requestPermissionLauncher.launch(arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ))
            },
        )
    } else {
        CenterLocationPrompt(
            onGetLocation = {
                requestPermissionLauncher.launch(arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ))
            },
            onReject = {
                permissionState.value = LocationPermissionState.DENIED
                onPermissionRejected()
            }
        )
    }
}
