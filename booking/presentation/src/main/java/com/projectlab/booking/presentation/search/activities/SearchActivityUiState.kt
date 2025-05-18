package com.projectlab.booking.presentation.search.activities

import com.projectlab.core.data.model.ActivityDto

data class SearchActivityUiState(
    val query: String = "",
    val activities: List<ActivityDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAllResults: Boolean = false,
    val address: String? = null
)