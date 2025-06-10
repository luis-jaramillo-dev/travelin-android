package com.projectlab.booking.presentation.booking.successful.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.component.ButtonSimple
import com.projectlab.core.presentation.designsystem.theme.onSurfaceLight
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun BookingSuccessfulBottom(
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.ScreenHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        ButtonSimple(
            text = stringResource(R.string.go_home),
            onClick = {
                onNextClick()
            },
            containerColor = Color.White,
            contentColor = onSurfaceLight
        )
    }
}