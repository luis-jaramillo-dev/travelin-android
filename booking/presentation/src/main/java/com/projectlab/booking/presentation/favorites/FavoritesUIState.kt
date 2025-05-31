package com.projectlab.booking.presentation.favorites

import com.projectlab.core.domain.entity.FavoriteActivityEntity

data class FavoritesUIState(
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val destinations: List<FavoriteActivityEntity> = emptyList(),
)
