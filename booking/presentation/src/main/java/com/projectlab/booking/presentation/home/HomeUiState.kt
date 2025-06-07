package com.projectlab.booking.presentation.home

import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.entity.FavoriteActivityEntity

data class HomeUiState(
    val query: String = "",
    val activities: List<ActivityDto> = emptyList(),
    val recommendedActivities: List<ActivityDto> = emptyList(),
    val recommendedHotels: List<ActivityDto> = emptyList(),
    val history: List<String> = emptyList(),
    val favoriteActivities: List<FavoriteActivityEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val hasLoadedRecommendations: Boolean = false
)
