package com.projectlab.travelin_android.flight.components.moleculas

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.projectlab.travelin_android.flight.components.atomos.PassengerPickerBottomSheet
import com.projectlab.travelin_android.flight.components.atomos.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassengerPickerField(
    passengerCount: Int,
    onPassengerCountChange: (Int) -> Unit,
    modifier: Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            PassengerPickerBottomSheet(
                currentCount = passengerCount,
                onConfirm = {
                    onPassengerCountChange(it)
                    showSheet = false
                },
                onDismiss = { showSheet = false }
            )
        }
    }

    Column {
        Text("Passengers: $passengerCount")
        PrimaryButton(
            text = "Add passengers",
            onClick = { showSheet = true },
            modifier = Modifier
        )
    }
}
