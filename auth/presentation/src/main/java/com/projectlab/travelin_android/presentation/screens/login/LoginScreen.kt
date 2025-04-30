package com.projectlab.travelin_android.presentation.screens.login

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.projectlab.travelin_android.presentation.screens.login.components.LoginBottomBar
import com.projectlab.travelin_android.presentation.screens.login.components.LoginContent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    Scaffold(
        content = { LoginContent(paddingValues = it) },
        bottomBar = { LoginBottomBar() }
    )
}