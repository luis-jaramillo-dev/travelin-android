package com.example.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onboarding.ui.screens.LoginScreen
import com.example.onboarding.ui.screens.Onboarding1Screen
import com.example.onboarding.ui.screens.Onboarding2Screen
import com.example.onboarding.ui.screens.Onboarding3Screen
import com.example.onboarding.ui.screens.SplashScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(navController)
        }
        composable<Onboarding1> {
            Onboarding1Screen { navController.navigate(Onboarding2) }
        }
        composable<Onboarding2> {
            Onboarding2Screen { navController.navigate(Onboarding3) }
        }
        composable<Onboarding3> {
            Onboarding3Screen { navController.navigate(Login) }
        }
        composable<Login> {
            LoginScreen { navController.navigate(Onboarding1) }
        }
    }
}
