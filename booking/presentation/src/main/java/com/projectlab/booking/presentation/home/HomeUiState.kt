package com.projectlab.booking.presentation.home

import com.projectlab.core.data.model.ActivityDto

data class HomeUiState(
    val query: String = "",
    val activities: List<ActivityDto> = emptyList(),
    val recommendedActivities: List<ActivityDto> = emptyList(),
    val history: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
