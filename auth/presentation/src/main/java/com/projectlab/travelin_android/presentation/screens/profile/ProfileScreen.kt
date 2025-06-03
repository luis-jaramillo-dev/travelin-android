package com.projectlab.travelin_android.presentation.screens.profile

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.travelin_android.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogoutClick: () -> Unit,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onTripsClick: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                BottomNavRoute.PROFILE,
                onHomeClick,
                onFavoritesClick,
                onTripsClick,
                onProfileClick = {},
            )
        },
    ) {
        ProfileContent(
            paddingValues = it,
            viewModel = viewModel,
            onLogoutClick = onLogoutClick,
        )
    }
}
