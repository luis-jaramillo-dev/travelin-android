package com.projectlab.core.presentation.designsystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.projectlab.core.presentation.designsystem.theme.TravelinTheme
import com.projectlab.core.presentation.designsystem.theme.spacing
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import com.projectlab.core.presentation.designsystem.R

/**
 * Displays a customizable date range picker dialog using `DateRangePicker`.
 *
 * This dialog allows users to select a date range, with support for:
 * - Specific selectable dates
 * - A continuous selectable date range
 * - Pre-selected start and end dates
 * - A minimum selectable year
 * - Custom dialog title text
 *
 * The dialog includes a title section and two action buttons styled via your design system.
 *
 * ## Usage
 *
 * The visibility of this dialog should be **controlled externally** using a `Boolean` state
 * (e.g. `remember { mutableStateOf(false) }`). This follows the recommended pattern in Jetpack Compose,
 * ensuring proper state-driven UI updates.
 *
 * ```kotlin
 * var showDialog by remember { mutableStateOf(false) }
 *
 * // Trigger dialog from a button or event
 * Button(onClick = { showDialog = true }) {
 *     Text("Open calendar")
 * }
 *
 * // Conditionally show the dialog based on state
 * if (showDialog) {
 *     FlexibleDateRangePickerDialog(
 *         onDismiss = { showDialog = false },
 *         onConfirm = { (start, end) ->
 *             viewModel.setSelectedDates(start, end)
 *             showDialog = false
 *         },
 *         availableDateRange = LocalDate.now()..LocalDate.now().plusDays(30),
 *         selectableYearFrom = 2023,
 *         initialStartDateMillis = someStartMillis,
 *         initialEndDateMillis = someEndMillis,
 *         titleText = stringResource(R.string.calendar_title)
 *     )
 * }
 * ```
 *
 * @param onDismiss Callback triggered when the dialog is dismissed without confirmation.
 * @param onConfirm Callback triggered when the user confirms their selection.
 * Returns a [Pair] of selected start and end dates in milliseconds (nullable if not selected).
 * @param availableDates A list of specific [LocalDate]s that can be selected. Optional.
 * @param availableDateRange A closed range of [LocalDate]s that are selectable. Optional.
 * @param initialStartDateMillis The initially selected start date in milliseconds. Optional.
 * @param initialEndDateMillis The initially selected end date in milliseconds. Optional.
 * @param selectableYearFrom The minimum selectable year (inclusive). Optional.
 * @param titleText The title displayed at the top of the picker. Defaults to [R.string.calendar_title].
 *
 * @requiresApi API level 26 (Oreo), as it uses [LocalDate] and [Instant].
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelinDatePicker(
    onDismiss: () -> Unit,
    onConfirm: (Pair<Long?, Long?>) -> Unit,
    availableDates: List<LocalDate>? = null,
    availableDateRange: ClosedRange<LocalDate>? = null,
    initialStartDateMillis: Long? = null,
    initialEndDateMillis: Long? = null,
    selectableYearFrom: Int? = null,
    titleText: String = stringResource(R.string.calendar_title)
) {

    val pickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = initialStartDateMillis,
        initialSelectedEndDateMillis = initialEndDateMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val date = Instant.ofEpochMilli(utcTimeMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

                return when {
                    availableDates != null -> availableDates.contains(date)
                    availableDateRange != null -> date in availableDateRange
                    else -> true
                }
            }

            override fun isSelectableYear(year: Int): Boolean {
                return selectableYearFrom?.let { year >= it } != false
            }
        }
    )
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
        ) {
            Column {
                DateRangePicker(
                    state = pickerState,
                    showModeToggle = false,
                    title = {
                        Text(
                            text = titleText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier
                                .padding(
                                    bottom = MaterialTheme.spacing.semiLarge,
                                )
                        )
                    },
                    headline = {},
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(24.dp)
                )
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
                            onConfirm(
                                pickerState.selectedStartDateMillis to pickerState.selectedEndDateMillis
                            )
                        },
                        text = stringResource(R.string.calendar_button_accept),
                        fullWidth = true,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FlexibleDateRangePickerDialogPreview() {
    TravelinTheme {
        TravelinDatePicker(
            onDismiss = {},
            onConfirm = {},
            availableDateRange = LocalDate.now()..LocalDate.now().plusDays(30),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelinDatePickerBottomSheet(
    onDismiss: () -> Unit,
    onConfirm: (Pair<Long?, Long?>) -> Unit,
    availableDates: List<LocalDate>? = null,
    availableDateRange: ClosedRange<LocalDate>? = null,
    initialStartDateMillis: Long? = null,
    initialEndDateMillis: Long? = null,
    selectableYearFrom: Int? = null,
    titleText: String = stringResource(R.string.calendar_title)
) {

    val pickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = initialStartDateMillis,
        initialSelectedEndDateMillis = initialEndDateMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val date = Instant.ofEpochMilli(utcTimeMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

                return when {
                    availableDates != null -> availableDates.contains(date)
                    availableDateRange != null -> date in availableDateRange
                    else -> true
                }
            }

            override fun isSelectableYear(year: Int): Boolean {
                return selectableYearFrom?.let { year >= it } != false
            }
        }
    )

    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column {
            DateRangePicker(
                state = pickerState,
                showModeToggle = false,
                title = {
                    Text(
                        text = titleText,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .padding(
                                bottom = MaterialTheme.spacing.semiLarge,
                            )
                    )
                },
                headline = {},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(24.dp)
            )
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
                    variant = ButtonVariant.Primary,
                    onClick = {
                        onConfirm(
                            pickerState.selectedStartDateMillis to pickerState.selectedEndDateMillis
                        )
                    },
                    text = stringResource(R.string.calendar_button_accept),
                    fullWidth = true,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}