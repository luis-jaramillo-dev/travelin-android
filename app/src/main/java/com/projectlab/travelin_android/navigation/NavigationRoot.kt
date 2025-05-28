package com.projectlab.travelin_android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.projectlab.booking.presentation.search.activities.SearchActivityScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.projectlab.booking.presentation.detail.activities.ActivityDetailScreen
import com.projectlab.booking.presentation.detail.activities.ActivityDetailViewModel
import com.projectlab.booking.presentation.detail.activities.ErrorScreen
import com.projectlab.booking.presentation.home.HomeScreen
import com.projectlab.booking.presentation.search.activities.SearchActivityViewModel
import com.projectlab.core.presentation.ui.di.LocationUtilsEntryPoint
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import com.projectlab.feature.onboarding.presentation.ui.OnboardingScreenRoot
import com.projectlab.travelin_android.presentation.screens.login.LoginScreen
import com.projectlab.travelin_android.presentation.screens.profile.ProfileScreen
import com.projectlab.travelin_android.presentation.screens.register.RegisterScreen
import com.projectlab.travelin_android.presentation.screens.successful.SuccessfulScreen
import dagger.hilt.android.EntryPointAccessors

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
        detailGraph(navController)
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthScreens.Onboarding.route,
        route = AuthScreens.Root.route
    ) {

        composable(route = AuthScreens.Onboarding.route) {
            OnboardingScreenRoot(
                viewModel = hiltViewModel(),
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
                onHomeClick = { navController.navigate(HomeScreens.Home.route) }
            )
        }

        composable(route = AuthScreens.Successful.route) {
            SuccessfulScreen(
                onProfileClick = { navController.navigate(AuthScreens.Profile.route) },
            )
        }
    }
}

private fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    composable(route = SearchScreens.Activities.route) {

        val context = LocalContext.current
        val locationUtils = remember {
            EntryPointAccessors
                .fromApplication(context.applicationContext, LocationUtilsEntryPoint::class.java)
                .locationUtils()
        }

        SearchActivityScreen(
            locationViewModel = hiltViewModel(),
            searchActivityViewModel = hiltViewModel(),
            locationUtils = locationUtils,
            navController = navController,
            onActivityClick = { activityId ->
                navController.navigate(DetailScreens.ActivityDetail.createRoute(activityId))
            }
        )
    }

    composable(
        route = SearchScreens.ActivitiesWithQuery.route,
        arguments = listOf(navArgument("query") { type = NavType.StringType })
    ) { backStackEntry ->

        val query = backStackEntry.arguments?.getString("query") ?: ""
        val context = LocalContext.current
        val locationUtils = remember {
            EntryPointAccessors
                .fromApplication(context.applicationContext, LocationUtilsEntryPoint::class.java)
                .locationUtils()
        }


        val locationViewModel: LocationViewModel = hiltViewModel()
        val searchActivityViewModel: SearchActivityViewModel = hiltViewModel()

        LaunchedEffect(query) {
            searchActivityViewModel.searchWithInitialQuery(query)
        }


        SearchActivityScreen(
            locationViewModel = locationViewModel,
            searchActivityViewModel = searchActivityViewModel,
            locationUtils = locationUtils,
            navController = navController,
            onActivityClick = { activityId ->
                navController.navigate(DetailScreens.ActivityDetail.createRoute(activityId))
            }
        )

    }
}

fun NavGraphBuilder.detailGraph(navController: NavHostController) {
    composable(
        route = DetailScreens.ActivityDetail.route,
        arguments = listOf(
            navArgument("activityId") { type = NavType.StringType }
        ),
        deepLinks = listOf(navDeepLink { uriPattern = "https://app.travelin.com/details/{activityId}" })
    ) { backStackEntry ->
        val activityId = backStackEntry.arguments?.getString("activityId") ?: ""

        ActivityDetailScreen(
            activityDetailViewModel = hiltViewModel<ActivityDetailViewModel>(),
            activityId = activityId,
            navController = navController
        )
    }

    composable(route = "error_screen/{message}",
        arguments = listOf(navArgument("message") { type = NavType.StringType })
    ) { backStackEntry ->
        val message = backStackEntry.arguments?.getString("message") ?: "Actividad no encontrada"
        ErrorScreen(
            message = message,
            onBack = { navController.navigate(HomeScreens.Home.route) }
        )
    }

}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(
        route = HomeScreens.Home.route,
        deepLinks = listOf(navDeepLink { uriPattern = "https://app.travelin.com/" })
    ) {

        val context = LocalContext.current
        val locationUtils = remember {
            EntryPointAccessors
                .fromApplication(context.applicationContext, LocationUtilsEntryPoint::class.java)
                .locationUtils()
        }

        HomeScreen(
            locationViewModel = hiltViewModel(),
            homeViewModel = hiltViewModel(),
            navController = navController,
            locationUtils = locationUtils
        )
    }

}