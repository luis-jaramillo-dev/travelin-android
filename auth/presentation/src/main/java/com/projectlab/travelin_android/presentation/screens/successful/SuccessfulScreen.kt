package com.projectlab.travelin_android.presentation.screens.successful

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.projectlab.core.presentation.designsystem.theme.inversePrimaryLight
import com.projectlab.core.presentation.designsystem.theme.primaryLight
import com.projectlab.travelin_android.presentation.screens.successful.components.SuccessfulContent

@Composable
fun SuccessfulScreen(
    onProfileClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryLight,
                        inversePrimaryLight
                    )
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = { },
            content = { SuccessfulContent(it, onProfileClick = onProfileClick) },
        )
    }
}