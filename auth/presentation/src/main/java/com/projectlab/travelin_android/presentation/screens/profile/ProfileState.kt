package com.projectlab.travelin_android.presentation.screens.profile

import com.projectlab.travelin_android.models.UserUi

data class ProfileState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val userUi: UserUi? = null
)