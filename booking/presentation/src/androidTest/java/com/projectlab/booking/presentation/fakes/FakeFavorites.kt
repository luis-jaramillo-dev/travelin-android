package com.projectlab.booking.presentation.fakes

import com.projectlab.core.domain.entity.FavoriteActivityEntity

val fakeFavoriteActivities = listOf(
    FavoriteActivityEntity(
        id = "1",
        name = "Eiffel Tower Tour",
        description = "A guided tour of the Eiffel Tower.",
        latitude = 48.8584,
        longitude = 2.2945,
        price = "25.00",
        currency = "EUR",
        pictures = listOf("https://example.com/eiffel.jpg"),
        minimumDuration = "2h",
        rating = 4.7f
    ),
    FavoriteActivityEntity(
        id = "2",
        name = "Louvre Museum Visit",
        description = "Explore the world-famous museum.",
        latitude = 48.8606,
        longitude = 2.3376,
        price = "17.00",
        currency = "EUR",
        pictures = listOf("https://example.com/louvre.jpg"),
        minimumDuration = "3h",
        rating = 4.8f
    )
)