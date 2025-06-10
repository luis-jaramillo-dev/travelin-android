package com.projectlab.travelin_android.presentation.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextDecoration
import com.projectlab.core.presentation.designsystem.R
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.components.ButtonSimple
import com.projectlab.travelin_android.presentation.components.OutlinedButtonWithIcons

@Composable
fun ProfileSettings(
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(R.string.account_settings),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

        OutlinedButtonWithIcons(
            icon = Icons.Default.AccountCircle,
            title = stringResource(R.string.edit_profile),
            onClick = { },
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        OutlinedButtonWithIcons(
            icon = Icons.Default.DarkMode,
            title = stringResource(R.string.color_mode),
            onClick = { },
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        ButtonSimple(
            text = stringResource(R.string.logout),
            onClick = {
                onLogoutClick()
            },
            modifier = Modifier
                .height(MaterialTheme.spacing.ProfileButtonHeight),
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            isOutlined = true,
            outlineAlpha = 0.15f,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
            ),
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

        Text(
            // TODO where do we get the version from?
            text = stringResource(R.string.version, "1.0.0"),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}
