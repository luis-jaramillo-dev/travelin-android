package com.projectlab.travelin_android.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.projectlab.booking.presentation.booking.hotels.BookingHotelScreen
import com.projectlab.booking.presentation.booking.successful.BookingSuccessfulScreen
import com.projectlab.booking.presentation.detail.activities.ActivityDetailScreen
import com.projectlab.booking.presentation.detail.activities.ActivityDetailViewModel
import com.projectlab.booking.presentation.favorites.FavoritesScreen
import com.projectlab.booking.presentation.favorites.FavoritesViewModel
import com.projectlab.booking.presentation.home.HomeScreen
import com.projectlab.booking.presentation.screens.HotelsViewModel
import com.projectlab.booking.presentation.screens.hotels.details.DetailHotelScreen
import com.projectlab.booking.presentation.screens.hotels.search.SearchHotelScreen
import com.projectlab.booking.presentation.search.activities.SearchActivityScreen
import com.projectlab.booking.presentation.search.activities.SearchActivityViewModel
import com.projectlab.core.presentation.ui.viewmodel.LocationViewModel
import com.projectlab.feature.onboarding.presentation.ui.OnboardingScreenRoot
import com.projectlab.travelin_android.presentation.screens.login.LoginScreen
import com.projectlab.travelin_android.presentation.screens.login.LoginViewModel
import com.projectlab.travelin_android.presentation.screens.profile.ProfileScreen
import com.projectlab.travelin_android.presentation.screens.profile.ProfileViewModel
import com.projectlab.travelin_android.presentation.screens.register.RegisterScreen
import com.projectlab.travelin_android.presentation.screens.register.RegisterViewModel
import com.projectlab.travelin_android.presentation.screens.successful.SuccessfulScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreens.Root.route,
    ) {
        authGraph(navController)
        searchGraph(navController)
        detailGraph(navController)
        homeGraph(navController)
        favoritesGraph(navController)
        bookingGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthScreens.Onboarding.route,
        route = AuthScreens.Root.route,
    ) {
        composable(route = AuthScreens.Onboarding.route) {
            OnboardingScreenRoot(
                viewModel = hiltViewModel(),
                onNavigateToLogin = { navController.navigate(AuthScreens.Login.route) },
            )
        }

        composable(route = AuthScreens.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel<LoginViewModel>(),
                onRegisterClick = { navController.navigate(AuthScreens.Register.route) },
                onLoggedIn = { navController.navigate(HomeScreens.Home.route) },
            )
        }

        composable(route = AuthScreens.Register.route) {
            RegisterScreen(
                viewModel = hiltViewModel<RegisterViewModel>(),
                onLoginClick = { navController.navigate(AuthScreens.Login.route) },
                onSuccessfulClick = { navController.navigate(AuthScreens.Successful.route) },
            )
        }

        composable(route = AuthScreens.Profile.route) {
            ProfileScreen(
                viewModel = hiltViewModel<ProfileViewModel>(),
                onLogoutClick = { navController.navigate(AuthScreens.Login.route) },
                onHomeClick = { navController.navigate(HomeScreens.Home.route) },
                onFavoritesClick = { navController.navigate(FavoritesScreens.Favorites.route) },
                onTripsClick = {},
            )
        }

        composable(route = AuthScreens.Successful.route) {
            SuccessfulScreen(
                onNextClick = { navController.navigate(HomeScreens.Home.route) },
            )
        }
    }
}

private fun NavGraphBuilder.searchGraph(navController: NavHostController) {

    composable(route = SearchScreens.Activities.route) {
        SearchActivityScreen(
            locationViewModel = hiltViewModel(),
            searchActivityViewModel = hiltViewModel(),
            favoritesViewModel = hiltViewModel(),
            navController = navController,
            onActivityClick = { activityId ->
                navController.navigate(DetailScreens.ActivityDetail.createRoute(activityId))
            }
        )


    }

    composable(
        route = SearchScreens.ActivitiesWithQuery.route,
        arguments = listOf(navArgument("query") { type = NavType.StringType }),
    ) { backStackEntry ->

        val query = backStackEntry.arguments?.getString("query") ?: ""

        val locationViewModel: LocationViewModel = hiltViewModel()
        val searchActivityViewModel: SearchActivityViewModel = hiltViewModel()
        val favoritesViewModel: FavoritesViewModel = hiltViewModel()

        LaunchedEffect(query) {
            searchActivityViewModel.searchWithInitialQuery(query)
        }

        SearchActivityScreen(
            locationViewModel = locationViewModel,
            searchActivityViewModel = searchActivityViewModel,
            favoritesViewModel = favoritesViewModel,
            navController = navController,
            onActivityClick = { activityId ->
                navController.navigate(DetailScreens.ActivityDetail.createRoute(activityId))
            },
        )

    }

    composable(route = SearchScreens.Hotels.route) {
        SearchHotelScreen(
            viewModel = hiltViewModel<HotelsViewModel>(),
            navController = navController
        )
    }
}

private fun NavGraphBuilder.detailGraph(navController: NavHostController) {
    composable(
        route = DetailScreens.ActivityDetail.route,
        arguments = listOf(
            navArgument("activityId") { type = NavType.StringType }
        ),
    ) { backStackEntry ->
        val activityId = backStackEntry.arguments?.getString("activityId") ?: ""

        ActivityDetailScreen(
            activityDetailViewModel = hiltViewModel<ActivityDetailViewModel>(),
            activityId = activityId,
            navController = navController,
            favoritesViewModel = hiltViewModel()
        )
    }

    composable(
        route = DetailScreens.HotelDetail.route,
        arguments = listOf(
            navArgument("hotelId") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val hotelId = backStackEntry.arguments?.getString("hotelId") ?: ""
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(SearchScreens.Hotels.route)
        }

        DetailHotelScreen(
            viewModel = hiltViewModel<HotelsViewModel>(parentEntry),
            onClickBack = { navController.popBackStack() },
            hotelId = hotelId
        )
    }

}

private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(route = HomeScreens.Home.route) {
        HomeScreen(
            locationViewModel = hiltViewModel(),
            homeViewModel = hiltViewModel(),
            navController = navController,
            onClickSearchHotel = { navController.navigate(SearchScreens.Hotels.route) },
            onFavoritesClick = { navController.navigate(FavoritesScreens.Favorites.route) },
            onTripsClick = {},
            onProfileClick = { navController.navigate(AuthScreens.Profile.route) },
            onItemClick = { activityId ->
                navController.navigate(DetailScreens.ActivityDetail.createRoute(activityId))
            },
            favoritesViewModel = hiltViewModel()
        )
    }
}

private fun NavGraphBuilder.favoritesGraph(navController: NavHostController) {
    composable(route = FavoritesScreens.Favorites.route) {
        FavoritesScreen(
            viewModel = hiltViewModel(),
            onHomeClick = { navController.navigate(HomeScreens.Home.route) },
            onTripsClick = {},
            onProfileClick = { navController.navigate(AuthScreens.Profile.route) },
            onActivityClick = { activityId ->
                navController.navigate(DetailScreens.ActivityDetail.createRoute(activityId))
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun NavGraphBuilder.bookingGraph(navController: NavHostController) {

    composable(route = BookingScreens.HotelBooking.route) { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry(SearchScreens.Hotels.route)
        }

        BookingHotelScreen(
            onSuccessBooking = { navController.navigate(BookingScreens.Successful.route) },
            onClickBack = { navController.popBackStack() },
            viewModel = hiltViewModel<HotelsViewModel>(parentEntry),
        )
    }

    composable(route = BookingScreens.Successful.route) {
        BookingSuccessfulScreen()
    }
}