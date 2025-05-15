package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PriceDisplay(
    amount: String,
    unit: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = amount,
            style = MaterialTheme.typography.titleMedium // Poppins Semibold 18sp
        )
        Text(
            text = unit,
            style = MaterialTheme.typography.bodySmall  // Poppins Regular 12sp
        )
    }
}
