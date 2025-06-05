package com.projectlab.travelin_android.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.theme.spacing

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .width(MaterialTheme.spacing.LoginLogoWidth)
            .height(MaterialTheme.spacing.LoginLogoHeight),
        painter = painterResource(R.drawable.logo_filled),
        contentDescription = stringResource(R.string.filled_discover_logo),
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))

    Text(
        text = stringResource(R.string.welcome_to_discover),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    )

    Text(
        text = stringResource(R.string.please_choose_your_login_option_below),
        style = MaterialTheme.typography.bodySmall,
    )
}
