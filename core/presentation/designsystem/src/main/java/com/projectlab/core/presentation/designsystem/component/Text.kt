package com.projectlab.core.presentation.designsystem.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SubTitle(
    text: String = ""
) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
    )
}