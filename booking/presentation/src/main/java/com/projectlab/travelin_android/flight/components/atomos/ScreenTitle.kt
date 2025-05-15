package com.projectlab.travelin_android.flight.components.atomos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,                    // Poppins Semibold 18sp
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Text(
            text = subtitle,
            fontSize = 12.sp,                    // Poppins Regular 12sp
            fontWeight = FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.8f), // ligeramente atenuado
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
