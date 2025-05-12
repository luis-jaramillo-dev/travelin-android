package com.projectlab.travelin_android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.projectlab.booking.presentation.activities.search.SearchActivityScreen
import com.projectlab.booking.presentation.activities.search.SearchActivityScreenWithHilt
import com.projectlab.booking.presentation.navigation.SearchScreens
import com.projectlab.feature.onboarding.presentation.ui.OnboardingScreen
import com.projectlab.travelin_android.presentation.screens.login.LoginScreen
import com.projectlab.travelin_android.presentation.screens.profile.ProfileScreen
import com.projectlab.travelin_android.presentation.screens.register.RegisterScreen
import com.projectlab.travelin_android.presentation.screens.successful.SuccessfulScreen

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreens.Root.route
    ) {
        authGraph(navController)
        searchGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthScreens.Onboarding.route,
        route = AuthScreens.Root.route
    ) {

        composable(route = AuthScreens.Onboarding.route) {
            OnboardingScreen(
                onNavigateToLogin = { navController.navigate(AuthScreens.Login.route) }
            )
        }

        composable(route = AuthScreens.Login.route) {
            LoginScreen(
                onRegisterClick = { navController.navigate(AuthScreens.Register.route) },
                onProfileClick = { navController.navigate(AuthScreens.Profile.route) }
            )
        }

        composable(route = AuthScreens.Register.route) {
            RegisterScreen(
                onLoginClick = { navController.navigate(AuthScreens.Login.route) },
                onSuccessfulClick = { navController.navigate(AuthScreens.Successful.route) },
            )
        }

        composable(route = AuthScreens.Profile.route) {
            ProfileScreen(
                onLogoutClick = { navController.navigate(AuthScreens.Login.route) },
                onHomeClick = { navController.navigate(SearchScreens.Activities.route) }
            )
        }

        composable(route = AuthScreens.Successful.route) {
            SuccessfulScreen(
                onProfileClick = { navController.navigate(AuthScreens.Profile.route) },
            )
        }
    }
}

private fun NavGraphBuilder.searchGraph(navController: NavHostController){
    composable(route = SearchScreens.Activities.route) {
        SearchActivityScreenWithHilt {

        }
    }
}