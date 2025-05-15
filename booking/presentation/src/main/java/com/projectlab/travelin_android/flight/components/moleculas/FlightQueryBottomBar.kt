package com.projectlab.travelin_android.flight.components.moleculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projectlab.travelin_android.flight.components.atomos.PriceDisplay
import com.projectlab.travelin_android.flight.components.atomos.PrimaryButton

@Composable
fun FlightQueryBottomBar(
    totalAmount: String,
    unit: String,
    onNext: ()->Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PriceDisplay(amount = totalAmount, unit = unit)
        PrimaryButton(
            text = "Next",
            onClick = onNext,
            modifier = Modifier.height(48.dp)
        )
    }
}
