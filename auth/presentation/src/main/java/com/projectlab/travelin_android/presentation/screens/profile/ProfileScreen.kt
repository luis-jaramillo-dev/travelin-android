package com.projectlab.travelin_android.presentation.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.projectlab.auth.presentation.R
import com.projectlab.core.presentation.designsystem.component.BottomNavRoute
import com.projectlab.core.presentation.designsystem.component.BottomNavigationBar
import com.projectlab.travelin_android.models.UserUI
import com.projectlab.travelin_android.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogoutClick: () -> Unit,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onTripsClick: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    when {
        state.loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            ProfileScreenScaffold(
                user = state.user,
                onLogoutClick = {
                    viewModel.logout()
                    onLogoutClick()
                },
                onHomeClick = onHomeClick,
                onFavoritesClick = onFavoritesClick,
                onTripsClick = onTripsClick,
            )

            if (state.error != null) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(
                        R.string.failed_to_get_current_user,
                        state.error ?: stringResource(R.string.unknown_error),
                    ),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@Composable
private fun ProfileScreenScaffold(
    user: UserUI?,
    onLogoutClick: () -> Unit,
    onHomeClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onTripsClick: () -> Unit,
    modifier: Modifier = Modifier,
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
    ) { paddingValues ->
        if (user != null) {
            ProfileContent(
                modifier = modifier.padding(paddingValues),
                onLogoutClick = onLogoutClick,
                user = user,
            )
        }
    }
}
