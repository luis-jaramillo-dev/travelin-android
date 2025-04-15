package com.projectlab.onboarding.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectlab.onboarding.presentation.R

val poppinsFont = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val CustomTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = poppinsFont,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = poppinsFont,
        fontSize = 16.sp
    )
)