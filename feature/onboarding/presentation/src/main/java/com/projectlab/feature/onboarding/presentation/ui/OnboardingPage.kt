package com.projectlab.feature.onboarding.presentation.ui

import androidx.compose.ui.graphics.painter.Painter

data class OnboardingPage(
    val title: String,
    val description: String,
    val backgroundImage: Painter,
)
