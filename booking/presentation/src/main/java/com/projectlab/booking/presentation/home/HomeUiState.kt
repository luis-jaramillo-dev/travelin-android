package com.projectlab.booking.presentation.home

import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.entity.FavoriteActivityEntity
import com.projectlab.core.domain.model.Hotel

data class HomeUiState(
    val query: String = "",
    val activities: List<ActivityDto> = emptyList(),
    val recommendedActivities: List<ActivityDto> = emptyList(),
    val recommendedHotels: List<Hotel> = emptyList(),
    val history: List<String> = emptyList(),
    val favoriteActivities: List<FavoriteActivityEntity> = emptyList(),
    val favoriteHotels: List<Hotel> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val hasLoadedRecommendations: Boolean = false,
    val isFavoriteLoading: Boolean
)
