package com.projectlab.core.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.window.Dialog
import com.projectlab.core.presentation.designsystem.ButtonPrimary
import com.projectlab.core.presentation.designsystem.ButtonSecondary
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel

@Composable
fun CenterLocationDialog(
    viewModel: LocationViewModel,
    onGetLocation: () -> Unit,
    onReject: () -> Unit,
    address: String?
) {
    val location = viewModel.location.value
    val buttonsVisible = remember { mutableStateOf(true) }

    Dialog(onDismissRequest = { onReject() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight()
        ) {
            TravelinTheme(dynamicColor = false, darkTheme = false) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (location == null)
                            stringResource(R.string.location_not_available)
                        else
                            stringResource(R.string.location) + ": $address",
                        style = MaterialTheme.typography.titleLarge
                    )

                    if (buttonsVisible.value) {
                        Row(
                            modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ButtonPrimary(
                                onClick = {
                                    buttonsVisible.value = false
                                    onGetLocation()
                                },
                                text = if (location == null)
                                    stringResource(R.string.get_location)
                                else
                                    stringResource(R.string.update_location),
                                fullWidth = false,
                                modifier = Modifier.semantics {
                                    contentDescription = "Give Location Permission"
                                }
                            )
                            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
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
    }
}