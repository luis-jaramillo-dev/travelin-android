package com.projectlab.booking.presentation.activities.detail

import com.projectlab.core.data.model.ActivityDto

data class ActivityDetailUiState (
    val activity: ActivityDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)