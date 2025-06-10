package com.projectlab.travelin_android.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.projectlab.core.presentation.designsystem.theme.spacing
import com.projectlab.travelin_android.presentation.screens.profile.ProfileViewModel

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.BigSpacing))
        ProfileUser(viewModel = viewModel)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.SectionSpacing))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.Spacer)
                .padding(horizontal = MaterialTheme.spacing.ScreenHorizontalPadding)
                .background(MaterialTheme.colorScheme.surfaceDim),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.ScreenHorizontalPadding))
        ProfileSettings(
            viewModel = viewModel,
            onLogoutClick = {
                viewModel.logout()
                onLogoutClick()
            },
        )
    }
}
