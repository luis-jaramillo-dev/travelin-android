package com.projectlab.travelin_android.flight.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DateRangePickerField(
    label: String,
    displayText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = displayText,
        onValueChange = { },
        label = { Text(label) },
        readOnly = true,
        leadingIcon = { Icon(Icons.Default.DateRange, null) },
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}