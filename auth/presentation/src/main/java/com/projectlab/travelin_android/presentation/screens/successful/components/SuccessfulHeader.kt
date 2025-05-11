package com.projectlab.travelin_android.presentation.screens.successful.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun SuccessfulHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.ScreenHorizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.white_logo_travelin),
            contentDescription = "Traveling logo in white",
            modifier = Modifier
                .width(MaterialTheme.spacing.LogoWidth)
                .height(MaterialTheme.spacing.LogoHeight),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SmallSpacing))

        Text(
            text = "Successfully created an account",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SmallSpacing))

        Text(
            text = "After this you can explore any place you want enjoy it!",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}