package com.projectlab.travelin_android.flight.components.atomos.flightqueryscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PassengerPickerBottomSheet(
    currentCount: Int,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var passengerCount by remember { mutableStateOf(currentCount) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecciona número de pasajeros",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { if (passengerCount > 1) passengerCount-- },
                enabled = passengerCount > 1
            ) {
                Icon(Icons.Default.Remove, contentDescription = "Disminuir")
            }

            Text(
                text = passengerCount.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            IconButton(
                onClick = { if (passengerCount < 9) passengerCount++ },
                enabled = passengerCount < 9
            ) {
                Icon(Icons.Default.Add, contentDescription = "Aumentar")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Confirmar",
            onClick = {
                onConfirm(passengerCount)
                onDismiss()
            }
        )

        TextButton(
            onClick = onDismiss,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Cancelar")
        }
    }
}