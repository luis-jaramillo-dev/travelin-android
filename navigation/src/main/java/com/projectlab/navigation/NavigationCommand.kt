package com.projectlab.navigation

sealed class NavigationCommand {

    data object NavigateUp: NavigationCommand()

    data class NavigateToRoute(
        val route: String,
        val options: androidx.navigation.NavOptions? = null
    ) : NavigationCommand()

    data class PopUpToRoute(
        val route: String,
        val inclusive: Boolean,
        val fallBackRoute: String,
    ) : NavigationCommand()

}