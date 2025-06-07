package com.projectlab.travelin_android.presentation.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.components.ButtonSimple
import com.projectlab.travelin_android.presentation.components.OutlinedButtonWithIcons

@Composable
fun ProfileSettings(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.ScreenHorizontalPadding)
    ) {
        Text(
            text = stringResource(R.string.account_setting),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

        OutlinedButtonWithIcons(
            icon = Icons.Default.AccountCircle,
            title = stringResource(R.string.edit_profile),
            onClick = { }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ElementSpacing))

        OutlinedButtonWithIcons(
            icon = Icons.Default.DarkMode,
            title = stringResource(R.string.color_mode),
            onClick = { }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.BigSpacing))

        ButtonSimple(
            text = stringResource(R.string.logout),
            onClick = {
                onLogoutClick()
            },
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            isOutlined = true
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ElementSpacing))

        Text(
            text = stringResource(R.string.version),
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
