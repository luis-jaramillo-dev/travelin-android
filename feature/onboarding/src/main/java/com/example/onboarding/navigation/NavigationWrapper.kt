package cl.travelin.onboarding.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import cl.travelin.onboarding.presentation.ui.screens.Onboarding1Screen
import cl.travelin.onboarding.presentation.ui.screens.Onboarding2Screen
import cl.travelin.onboarding.presentation.ui.screens.Onboarding3Screen
import cl.travelin.onboarding.presentation.ui.screens.LoginScreen
import cl.travelin.onboarding.presentation.ui.screens.SplashScreen

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(
        navController= navController,
        startDestination = Splash
        ){
        composable<Splash>{
            SplashScreen(navController)
        }
        composable<Onboarding1>{
            Onboarding1Screen { navController.navigate(Onboarding2) }
        }
        composable<Onboarding2>{
            Onboarding2Screen { navController.navigate(Onboarding3) }
        }
        composable<Onboarding3>{
            Onboarding3Screen { navController.navigate(Login) }
        }
        composable<Login>{
            LoginScreen { navController.navigate(Onboarding1) }
        }
    }
}