package com.projectlab.booking.presentation.booking.successful

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.projectlab.booking.presentation.booking.successful.components.BookingSuccessfulContent
import com.projectlab.booking.presentation.booking.successful.components.BookingSuccessfulHeader
import com.projectlab.core.presentation.designsystem.theme.inversePrimaryLight
import com.projectlab.core.presentation.designsystem.theme.primaryLight

@Composable
fun BookingSuccessfulScreen(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit
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
            content = {
                BookingSuccessfulContent(
                    modifier = modifier.padding(it),
                    onNextClick = onHomeClick
                )
            },
        )
    }
}