package com.projectlab.core.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.ui.R

@Composable
fun CenterLocationPrompt(
    onGetLocation: () -> Unit,
    onReject: () -> Unit
) {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.location_not_available),
                style = MaterialTheme.typography.titleLarge
            )
            Row(modifier = Modifier.padding(top = MaterialTheme.spacing.medium)) {
                ButtonComponent(
                    onClick = onGetLocation,
                    text = stringResource(R.string.get_location),
                    fullWidth = false,
                    variant = ButtonVariant.Primary,
                    modifier = Modifier.semantics {
                        contentDescription = "Give Location Permission"
                    })
                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))
                ButtonComponent(
                    onClick = onReject,
                    text = stringResource(R.string.no_thanks),
                    fullWidth = false,
                    variant = ButtonVariant.Secondary,
                    modifier = Modifier.semantics {
                        contentDescription = "Reject Location Permission"
                    })
            }
        }
    }
}