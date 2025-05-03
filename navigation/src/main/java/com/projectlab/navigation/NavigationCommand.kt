package com.projectlab.navigation

sealed class NavigationCommand {

    data object NavigateUp: NavigationCommand()

    data class NavigateToRoute(
        val route: Any,
        val options: androidx.navigation.NavOptions? = null
    ) : NavigationCommand()

    data class PopUpToRoute(
        val route: Any,
        val inclusive: Boolean,
        val fallBackRoute: Any,
    ) : NavigationCommand()

}