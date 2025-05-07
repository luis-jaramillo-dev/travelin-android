package com.projectlab.core.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
// import com.projectlab.core.presentation.designsystem.ButtonPrimary
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.ui.R

@Composable
fun BottomLocationBar(
    onGetLocation: () -> Unit,
) {
    TravelinTheme(dynamicColor = false, darkTheme = false) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                stringResource(id = R.string.results_near_you),
                style = MaterialTheme.typography.titleMedium
            )
            // TODO: Uncomment this button (and import) when button is ready
//            ButtonPrimary(
//                onClick = onGetLocation,
//                text = stringResource(id = R.string.get_location),
//                fullWidth = false,
//                modifier = Modifier.semantics { contentDescription = "Give Location Permission" }
//            )
        }
    }
}