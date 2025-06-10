package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun LoginBottomBar(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.regular)
            .navigationBarsPadding()
            .clickable { onRegisterClick() },
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Doesn’t have account on discover?",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
        )

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))

        Text(
            text = "Create Account",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            ),
        )
    }
}
