package com.projectlab.travelin_android.presentation.screens.successful

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    onNextClick: () -> Unit,
) {
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        primaryLight,
                        inversePrimaryLight,
                    ),
                ),
            )
    ) { paddingValues ->
        SuccessfulContent(
            modifier = Modifier.padding(paddingValues),
            onNextClick = onNextClick,
        )
    }
}
