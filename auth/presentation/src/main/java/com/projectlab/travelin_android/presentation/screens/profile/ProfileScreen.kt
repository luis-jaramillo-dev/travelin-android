package com.projectlab.travelin_android.presentation.screens.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    if (state.userUi != null) {
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
                modifier = modifier
                    .fillMaxSize()
                    .padding(it),
                onLogoutClick = {
                    viewModel.logout()
                    onLogoutClick()
                },
                userUi = state.userUi!!
            )
        }
    }
}