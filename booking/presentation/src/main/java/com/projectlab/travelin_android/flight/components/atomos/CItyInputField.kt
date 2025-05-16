package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.projectlab.core.presentation.designsystem.theme.customShapes
import com.projectlab.travelin_android.model.CityLocation

@Composable
fun CityInputField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    suggestions: List<CityLocation>,
    onSuggestionClick: (CityLocation) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.customShapes.large,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedBorderColor   = MaterialTheme.colorScheme.outline,
                cursorColor          = MaterialTheme.colorScheme.primary,
                focusedLabelColor    = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor  = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        AnimatedVisibility(visible = suggestions.isNotEmpty()) {
            Column {
                suggestions.take(5).forEach { suggestion ->
                    TextButton(
                        onClick = { onSuggestionClick(suggestion) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("${suggestion.city} — ${suggestion.iataCode}")
                    }
                }
            }
        }
    }
}