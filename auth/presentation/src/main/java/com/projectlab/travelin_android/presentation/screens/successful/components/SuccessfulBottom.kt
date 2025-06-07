package com.projectlab.travelin_android.presentation.screens.successful.components

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
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.onSurfaceLight
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.components.ButtonSimple

@Composable
fun SuccessfulBottom(
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.ScreenHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        ButtonSimple(
            text = stringResource(R.string.lets_explore),
            onClick = {
                onNextClick()
            },
            containerColor = Color.White,
            contentColor = onSurfaceLight,
        )
    }
}
