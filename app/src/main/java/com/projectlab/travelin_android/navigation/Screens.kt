package com.projectlab.travelin_android.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

interface Screens {
    val icon: ImageVector?
        get() = null
    val route: String
}

sealed interface AuthScreens {
    data object Onboarding : AuthScreens, Screens {
        override val route = "onboarding"
    }

    data object Root : AuthScreens, Screens {
        override val route = "auth"
    }

    data object Login : AuthScreens, Screens {
        override val route = "login"
    }

    data object Register : AuthScreens, Screens {
        override val route = "register"
    }

    data object Profile : AuthScreens, Screens {
        override val route = "profile"
    }

    data object Successful : AuthScreens, Screens {
        override val route = "successful"
    }
}

sealed interface SearchScreens {
    val route: String

    data object Activities : SearchScreens {
        override val route = "search_activities"
    }

    data object ActivitiesWithQuery : SearchScreens {
        override val route = "search_activities_with_query/{query}"
        fun createRoute(query: String): String = "$route/$query"
    }

    data object Hotels : SearchScreens {
        override val route = "search_hotels"
    }


    data object Flights : SearchScreens {
        override val route = "search_flights"
    }
}

sealed interface DetailScreens {
    val route: String

    @Serializable
    data object ActivityDetail : DetailScreens {
        override val route = "activityDetail/{activityId}"
        fun createRoute(activityId: String): String = "activityDetail/$activityId"
    }

    @Serializable
    data object HotelDetail : DetailScreens {
        override val route = "hotelDetail/{hotelId}"
        fun createRoute(hotelId: String): String = "hotelDetail/$hotelId"
    }
}

sealed interface HomeScreens {
    val route: String

    data object Home : HomeScreens {
        override val route = "home"
    }
}

sealed interface FavoritesScreens {
    val route: String

    data object Favorites : FavoritesScreens {
        override val route = "favorites"
    }
}

sealed interface ItinerariesScreens {
    val route: String

    data object Itineraries : ItinerariesScreens {
        override val route = "itineraries"
    }
}
sealed interface BookingScreens {
    val route: String

    data object HotelBooking : BookingScreens {
        override val route = "hotel_booking"
    }

    data object Successful : BookingScreens, Screens {
        override val route = "successful_booking"
    }
}
