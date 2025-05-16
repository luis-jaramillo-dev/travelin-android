package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.projectlab.core.presentation.designsystem.theme.customShapes

@Composable
fun LabelledDropdown(
    label: String,
    selected: String,
    placeholder: String,
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )
        var expanded by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = if (selected.isNotEmpty()) selected else placeholder,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            shape = MaterialTheme.customShapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor   = MaterialTheme.colorScheme.outline,
                cursorColor          = MaterialTheme.colorScheme.primary,
                focusedLabelColor    = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor  = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
