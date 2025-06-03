package com.projectlab.booking.presentation.detail.activities

import com.projectlab.core.data.model.ActivityDto

data class ActivityDetailUiState(
    val activity: ActivityDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorite: Boolean = false,
    val isFavoriteLoading: Boolean = false,
)
