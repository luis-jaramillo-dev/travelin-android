package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DateRangePickerField(
    label: String,
    dateRange: Pair<String, String>?,     // e.g. ("11 Apr.", "15 Apr.")
    placeholder: String = "",
    onDateRangeSelected: (Pair<String, String>) -> Unit,
    modifier: Modifier = Modifier
) {
    val displayText = dateRange
        ?.let { "${it.first} – ${it.second}" }
        ?: placeholder

    Column(modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall  // Poppins 10sp
        )
        OutlinedTextField(
            value = displayText,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // aquí dispararías tu DateRangePicker
                    // por ejemplo: showDatePicker { from, to -> onDateRangeSelected(from to to) }
                },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium // Poppins 12sp
                )
            }
        )
    }
}