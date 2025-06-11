package com.projectlab.booking.presentation.booking.hotels.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.projectlab.core.data.mock.CountryCodes
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.ButtonComponent
import com.projectlab.core.presentation.designsystem.component.ButtonVariant
import com.projectlab.core.presentation.designsystem.component.TravelinSecureTextField
import com.projectlab.core.presentation.designsystem.component.TravelinTextFieldColumn
import com.projectlab.core.presentation.designsystem.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingHotelAddGuest(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var stateGuestName: TextFieldState = remember { TextFieldState() }
    var stateGuestNumber: TextFieldState = remember { TextFieldState() }
    var stateCountryCode: TextFieldState = remember { TextFieldState() }
    var stateEmail: TextFieldState = remember { TextFieldState() }
    var stateIdNumber: TextFieldState = remember { TextFieldState() }
    val indexItem: MutableIntState = remember { mutableIntStateOf(0) }


    ModalBottomSheet(
        onDismissRequest = { onDismiss() },

        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },

        ) {
        Column(
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = "Detail Booking",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Get the best out of derleng by creating an account",
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            TravelinTextFieldColumn(


                state = stateGuestName,
                title = "Guest name",
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))


            TravelinTextFieldColumn(
                state = stateGuestNumber,
                title = "Guest number",
                keyboardType = KeyboardType.Number,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))


            TravelinTextFieldColumn(
                modifier = modifier,
                state = stateCountryCode,
                items = CountryCodes.countryCodes,
                selectedItem = indexItem,
                title = "Phone",

                )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            TravelinTextFieldColumn(
                state = stateEmail,
                title = "Email number",
                keyboardType = KeyboardType.Email,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            TravelinSecureTextField(
                state = stateIdNumber,
                title = "ID number",
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = MaterialTheme.spacing.medium,
                        clip = false
                    )
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                ButtonComponent(
                    variant = ButtonVariant.Tertiary,
                    onClick = { onDismiss() },
                    text = stringResource(R.string.calendar_button_cancel),
                    fullWidth = true,
                    modifier = Modifier.weight(1f)
                )
                ButtonComponent(
                    variant = ButtonVariant.Primary,
                    onClick = {
                        onConfirm()
                    },
                    text = "Save",
                    fullWidth = true,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }


}