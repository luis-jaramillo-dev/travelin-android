package com.projectlab.navigation

import androidx.navigation.NavController

class NavigationManager(
    private val navController: NavController
) {

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.NavigateToRoute -> {
                navController.navigate(command.route)
            }

            NavigationCommand.NavigateUp -> navController.navigateUp()

            is NavigationCommand.PopUpToRoute -> {
                val hasPopped = navController.popBackStack(
                    command.route,
                    command.inclusive,
                )
                if (!hasPopped) {
                    navController.popBackStack(
                        command.fallBackRoute,
                        command.inclusive,
                    )
                }
            }
        }
    }

}