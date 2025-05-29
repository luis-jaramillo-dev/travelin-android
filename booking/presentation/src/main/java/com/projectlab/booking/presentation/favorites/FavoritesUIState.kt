package com.projectlab.booking.presentation.favorites

import com.projectlab.core.data.model.ActivityDto

data class FavoritesUIState(
    val query: String = "",
    val destinations: List<ActivityDto> = emptyList(),
    val hotels: List<ActivityDto> = emptyList(),
)
