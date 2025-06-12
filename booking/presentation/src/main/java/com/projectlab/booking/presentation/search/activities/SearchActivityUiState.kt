package com.projectlab.booking.presentation.search.activities

import com.projectlab.core.data.model.ActivityDto
import com.projectlab.core.domain.entity.FavoriteActivityEntity

enum class SearchOrigin {
    QUERY,
    LOCATION
}

data class SearchActivityUiState(
    val query: String = "",
    val activities: List<ActivityDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAllResults: Boolean = false,
    val address: String? = null,
    val isFavoriteLoading: Boolean = false,
    val favoriteActivities: List<FavoriteActivityEntity> = emptyList(),
    val history: List<String> = emptyList(),
    val searchOrigin: SearchOrigin = SearchOrigin.QUERY
)
