package com.projectlab.core.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.ButtonPrimary
import com.projectlab.core.presentation.designsystem.ButtonSecondary
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.R
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel

@Composable
fun CenterLocationPrompt(
    viewModel: LocationViewModel,
    onGetLocation: () -> Unit,
    onReject: () -> Unit,
    address: String?,
) {
    val location = viewModel.location.value
    val buttonsVisible = remember { mutableStateOf(true) }


    TravelinTheme(dynamicColor = false, darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (location == null) {
                Text(
                    stringResource(R.string.location_not_available),
                    style = MaterialTheme.typography.titleLarge
                )
            } else {
                Text(
                    text = stringResource(R.string.location) + ": $address",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            if (buttonsVisible.value) {
                Row(modifier = Modifier.padding(top = MaterialTheme.spacing.medium)) {
                    ButtonPrimary(
                        onClick = {
                            buttonsVisible.value = false
                            onGetLocation()
                        },
                        text = if (location == null) stringResource(R.string.get_location) else stringResource(R.string.update_location),
                        fullWidth = false,
                        modifier = Modifier.semantics {
                            contentDescription = "Give Location Permission"
                        }
                    )
                    Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))
                    ButtonSecondary(
                        onClick = {
                            buttonsVisible.value = false
                            onReject()
                        },
                        text = stringResource(R.string.no_thanks),
                        fullWidth = false,
                        modifier = Modifier.semantics {
                            contentDescription = "Reject Location Permission"
                        }
                    )
                }
            }
        }
    }
}