package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LabelledTextField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String)->Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = label, style = MaterialTheme.typography.labelSmall) // Poppins 10
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, style = MaterialTheme.typography.bodyMedium) }, // Poppins 12
            modifier = Modifier.fillMaxWidth()
        )
    }
}