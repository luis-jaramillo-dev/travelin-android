package com.projectlab.booking.presentation.navigation

sealed interface SearchScreens {
    val route: String

    data object Activities : SearchScreens {
        override val route = "search_activities"
    }

    data object Hotels : SearchScreens {
        override val route = "search_hotels"
    }

    data object Flights : SearchScreens {
        override val route = "search_flights"
    }
}