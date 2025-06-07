package com.projectlab.travelin_android.presentation.screens.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun RegisterHeader(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
) {
    Column(modifier = modifier) {
        IconButton(onClick = onLoginClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = stringResource(R.string.go_back),
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.regular))

        Text(
            text = stringResource(R.string.create_account_header),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
            ),
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        Text(
            text = stringResource(R.string.get_the_best_out_of_discover_by_creating_an_account),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
        )
    }
}
