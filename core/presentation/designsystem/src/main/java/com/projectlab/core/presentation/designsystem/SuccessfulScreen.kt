package com.projectlab.core.presentation.designsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SuccessfulScreen(
    onClick: () -> Unit,
    title: String,
    subtitle: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    image: Int = R.drawable.white_logo_travelin
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.63f to MaterialTheme.colorScheme.primary,
                        1.0f to MaterialTheme.colorScheme.primaryContainer
                    ),
                )
            )
            .padding(bottom = 50.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(250.dp))

            Image(
                painter = painterResource(image),
                contentDescription = "Traveling logo",
                modifier = Modifier
                    .size(150.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        ButtonSecondary(
            text = buttonText,
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(55.dp)
                .padding(horizontal = 28.dp)
        )

    }
}

