package com.projectlab.core.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.ButtonPrimary
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.ui.R
import com.projectlab.core.presentation.ui.model.LocationData

@Composable
fun BottomLocationBar(
    location: LocationData?,
    onGetLocation: () -> Unit,
) {
    val labelText = if (location == null) {
        stringResource(id = R.string.results_near_you)
    } else {
        stringResource(id = R.string.did_you_change_location)
    }

    val buttonText = if (location == null) {
        stringResource(id = R.string.get_location)
    } else {
        stringResource(id = R.string.update)
    }

    TravelinTheme(dynamicColor = false, darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (location == null)
                    stringResource(R.string.location_not_available)
                else
                    stringResource(R.string.location) + ": ${location.latitude}, ${location.longitude}",
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    labelText,
                    style = MaterialTheme.typography.titleMedium
                )
                ButtonPrimary(
                    onClick = onGetLocation,
                    text = buttonText,
                    fullWidth = false,
                    modifier = Modifier.semantics {
                        contentDescription = "Give Location Permission"
                    }
                )
            }
        }
    }
}