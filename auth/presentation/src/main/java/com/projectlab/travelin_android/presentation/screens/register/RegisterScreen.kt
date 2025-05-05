package com.projectlab.travelin_android.presentation.screens.register

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.projectlab.travelin_android.presentation.screens.register.components.RegisterBottomBar
import com.projectlab.travelin_android.presentation.screens.register.components.RegisterContent

@Composable
fun RegisterScreen(

) {
    Scaffold(
        topBar = { },
        content = { RegisterContent(it) },
        bottomBar = { RegisterBottomBar() }
    )
}