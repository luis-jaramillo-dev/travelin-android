package com.projectlab.travelin_android.presentation.navigation

sealed class AuthScreens(val route: String) {
    object Login : AuthScreens("login")
}