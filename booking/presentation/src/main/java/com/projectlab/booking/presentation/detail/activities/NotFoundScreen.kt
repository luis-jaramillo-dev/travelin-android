package com.projectlab.booking.presentation.detail.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.core.presentation.designsystem.R

@Composable
fun ErrorScreen(
    message: String = "Oops! Something went wrong :(",
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text= message, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(MaterialTheme.spacing.semiLarge))
        Button(onClick = onBack) {
            Text(text= stringResource(R.string.backToHome))
        }
    }
}
