package com.projectlab.travelin_android.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.models.UserUI

@Composable
fun ProfileContent(
    user: UserUI,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(top = MaterialTheme.spacing.BigSpacing)
            .padding(horizontal = MaterialTheme.spacing.semiLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileUser(user = user)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.Spacer)
                .background(MaterialTheme.colorScheme.surfaceDim),
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenHorizontalPadding))

        ProfileSettings(onLogoutClick = onLogoutClick)
    }
}
