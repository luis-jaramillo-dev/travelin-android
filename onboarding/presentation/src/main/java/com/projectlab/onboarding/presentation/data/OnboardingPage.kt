package com.projectlab.onboarding.presentation.data

import androidx.compose.ui.graphics.painter.Painter

data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Painter
)