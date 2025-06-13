package com.projectlab.booking.presentation.favorites

import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.entity.FavoriteHotelEntity

data class FavoritesUIState(
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val activities: List<FavoriteActivityEntity> = emptyList(),
    val hotels: List<FavoriteHotelEntity> = emptyList(),
    val favoriteActivities: List<FavoriteActivityEntity> = emptyList(),
    val isFavoriteLoading: Boolean = false,
    val isFavoriteHotel: Boolean = false
)
