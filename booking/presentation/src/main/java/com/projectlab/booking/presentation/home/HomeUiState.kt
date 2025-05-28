package com.projectlab.booking.presentation.home

import com.projectlab.core.data.model.ActivityDto

data class HomeUiState(
    val query: String = "",
    val activities: List<ActivityDto> = emptyList(),
    val history: List<String> = emptyList(),
)
