package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.projectlab.booking.presentation.R

@Composable
fun BackButton(onBack: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onBack, modifier = modifier.size(width = 25.dp, height = 46.dp)) {
        Icon(painterResource(R.drawable.ic_back), contentDescription = "Back")
    }
}